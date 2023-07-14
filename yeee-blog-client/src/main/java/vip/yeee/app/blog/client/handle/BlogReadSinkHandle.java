package vip.yeee.app.blog.client.handle;

import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;
import vip.yeee.app.blog.client.service.ApiBlogArticleService;

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
public class BlogReadSinkHandle implements SinkHandle {

    @Resource
    private ApiBlogArticleService apiBlogArticleService;

    @Override
    public void sink(List<BlogStatsRequest> data) {
        Map<String, List<BlogStatsRequest>> blogIdMap = data.stream().collect(Collectors.groupingBy(BlogStatsRequest::getSubjectId));
        blogIdMap.forEach((blogId, d) -> apiBlogArticleService.incrBlogReadNum(blogId, d.size()));
    }
}
