package vip.yeee.app.blog.manage.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.manage.model.vo.BlogAccessLogListVo;
import vip.yeee.app.blog.manage.service.BlogAccessLogService;
import vip.yeee.app.common.domain.mysql.entity.BlogAccessLog;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-07-17 10:00:34
*/
@Component
public class BlogAccessLogBiz {

    @Resource
    private BlogAccessLogService blogAccessLogService;

    public PageVO<BlogAccessLogListVo> blogAccessLogPageList(String query) {
        IPage<BlogAccessLog> page = blogAccessLogService.blogAccessLogPageList(query);
        PageVO<BlogAccessLogListVo> pageVO = new PageVO<>((int)page.getCurrent(), (int)page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<BlogAccessLogListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    BlogAccessLogListVo vo = new BlogAccessLogListVo();
                    BeanUtils.copyProperties(po, vo);
                    vo.setId(po.getId().toString());
                    return vo;
                })
                .collect(Collectors.toList());
        pageVO.setPages((int) page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setResult(voList);
        return pageVO;
    }
}
