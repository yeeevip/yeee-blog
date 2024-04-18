package vip.yeee.app.blog.client.handle;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.biz.ApiBlogArticleBiz;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;
import vip.yeee.app.blog.client.model.vo.ApiBlogArticleDetailVo;
import vip.yeee.app.blog.client.service.ApiBlogArticleService;
import vip.yeee.app.common.domain.mysql.entity.BlogAccessLog;
import vip.yeee.app.common.model.request.IdRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 11:48
 */
@Component
public class ArticleLikeSinkHandle extends AbstractSinkHandle {

    @Resource
    private ApiBlogArticleService apiBlogArticleService;
    @Resource
    private ApiBlogArticleBiz apiBlogArticleBiz;

    @Override
    public void sink(List<BlogStatsRequest> data) {
        Map<String, List<BlogStatsRequest>> blogIdMap = data.stream().collect(Collectors.groupingBy(BlogStatsRequest::getSubjectId));
        blogIdMap.forEach((blogId, d) -> {
            apiBlogArticleService.incrBlogLikeNum(blogId, d.size());
            ThreadUtil.execAsync(() -> this.saveAccessLog(d));
        });
    }

    @Override
    public String genAccessLogRemark(BlogAccessLog save) {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(save.getSubjectId());
        ApiBlogArticleDetailVo articleDetailVo = apiBlogArticleBiz.blogArticleDetail(idRequest);
        return StrUtil.format("用户{}[{}]‘点赞’了文章‘{}’", save.getClientIp(), save.getClientAddress(), articleDetailVo.getTitle());
    }
}
