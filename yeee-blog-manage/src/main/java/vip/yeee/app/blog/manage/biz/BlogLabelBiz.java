package vip.yeee.app.blog.manage.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.manage.model.request.BlogLabelAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogLabelUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogLabelInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogLabelListVo;
import vip.yeee.app.blog.manage.service.BlogLabelService;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogLabel;
import vip.yeee.memo.base.model.exception.BizException;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 14:05:11
*/
@Component
public class BlogLabelBiz {

    @Resource
    private BlogLabelService blogLabelService;

    public PageVO<BlogLabelListVo> blogLabelPageList(String query) {
        IPage<BlogLabel> page = blogLabelService.blogLabelPageList(query);
        PageVO<BlogLabelListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<BlogLabelListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    BlogLabelListVo vo = new BlogLabelListVo();
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

    public Void blogLabelAdd(BlogLabelAddRequest request) {
        BlogLabel saveModel = new BlogLabel();
        BeanUtils.copyProperties(request, saveModel);
        blogLabelService.blogLabelAdd(saveModel);
        return null;
    }

    public Void blogLabelUpd(BlogLabelUpdRequest request) {
        BlogLabel blogLabel = blogLabelService.queryBlogLabelById(request.getId());
        if (blogLabel == null) {
            throw new BizException("不存在");
        }
        BlogLabel updModel = new BlogLabel();
        BeanUtils.copyProperties(request, updModel);
        blogLabelService.blogLabelUpd(updModel);
        return null;
    }

    public BlogLabelInfoVo blogLabelInfo(IdRequest request) {
        if (request.getId() == null) {
            throw new BizException("ID不能为空");
        }
        BlogLabel blogLabel = blogLabelService.queryBlogLabelById(request.getId());
        if (blogLabel == null) {
            throw new BizException("不存在");
        }
        BlogLabelInfoVo infoVo = new BlogLabelInfoVo();
        BeanUtils.copyProperties(blogLabel, infoVo);
        infoVo.setId(blogLabel.getId().toString());
        return infoVo;
    }

    public Void blogLabelDel(IdRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            throw new BizException("IDS不能为空");
        }
        // BlogLabel blogLabel = blogLabelService.queryBlogLabelById(request.getId());
        // if (blogLabel == null) {
        //     throw new BizException("不存在");
        // }
        blogLabelService.blogLabelDel(request.getIds());
        return null;
    }

}
