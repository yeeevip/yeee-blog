package vip.yeee.app.blog.manage.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.manage.model.request.BlogTopicAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogTopicUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogTopicInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogTopicListVo;
import vip.yeee.app.blog.manage.service.BlogTopicService;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogTopic;
import vip.yeee.memo.base.model.exception.BizException;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 14:06:10
*/
@Component
public class BlogTopicBiz {

    @Resource
    private BlogTopicService blogTopicService;

    public PageVO<BlogTopicListVo> blogTopicPageList(String query) {
        IPage<BlogTopic> page = blogTopicService.blogTopicPageList(query);
        PageVO<BlogTopicListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<BlogTopicListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    BlogTopicListVo vo = new BlogTopicListVo();
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

    public Void blogTopicAdd(BlogTopicAddRequest request) {
        BlogTopic saveModel = new BlogTopic();
        BeanUtils.copyProperties(request, saveModel);
        blogTopicService.blogTopicAdd(saveModel);
        return null;
    }

    public Void blogTopicUpd(BlogTopicUpdRequest request) {
        BlogTopic blogTopic = blogTopicService.queryBlogTopicById(request.getId());
        if (blogTopic == null) {
            throw new BizException("不存在");
        }
        BlogTopic updModel = new BlogTopic();
        BeanUtils.copyProperties(request, updModel);
        blogTopicService.blogTopicUpd(updModel);
        return null;
    }

    public BlogTopicInfoVo blogTopicInfo(IdRequest request) {
        if (request.getId() == null) {
            throw new BizException("ID不能为空");
        }
        BlogTopic blogTopic = blogTopicService.queryBlogTopicById(request.getId());
        if (blogTopic == null) {
            throw new BizException("不存在");
        }
        BlogTopicInfoVo infoVo = new BlogTopicInfoVo();
        BeanUtils.copyProperties(blogTopic, infoVo);
        infoVo.setId(blogTopic.getId().toString());
        return infoVo;
    }

    public Void blogTopicDel(IdRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            throw new BizException("IDS不能为空");
        }
        // BlogTopic blogTopic = blogTopicService.queryBlogTopicById(request.getId());
        // if (blogTopic == null) {
        //     throw new BizException("不存在");
        // }
        blogTopicService.blogTopicDel(request.getIds());
        return null;
    }

}
