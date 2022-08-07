package vip.yeee.app.blog.manage.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.manage.model.request.BlogClassifyAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogClassifyUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogClassifyInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogClassifyListVo;
import vip.yeee.app.blog.manage.service.BlogClassifyService;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogClassify;
import vip.yeee.memo.base.model.exception.BizException;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 13:53:44
*/
@Component
public class BlogClassifyBiz {

    @Resource
    private BlogClassifyService blogClassifyService;

    public PageVO<BlogClassifyListVo> blogClassifyPageList(String query) {
        IPage<BlogClassify> page = blogClassifyService.blogClassifyPageList(query);
        PageVO<BlogClassifyListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<BlogClassifyListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    BlogClassifyListVo vo = new BlogClassifyListVo();
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

    public Void blogClassifyAdd(BlogClassifyAddRequest request) {
        BlogClassify saveModel = new BlogClassify();
        BeanUtils.copyProperties(request, saveModel);
        blogClassifyService.blogClassifyAdd(saveModel);
        return null;
    }

    public Void blogClassifyUpd(BlogClassifyUpdRequest request) {
        BlogClassify blogClassify = blogClassifyService.queryBlogClassifyById(request.getId());
        if (blogClassify == null) {
            throw new BizException("不存在");
        }
        BlogClassify updModel = new BlogClassify();
        BeanUtils.copyProperties(request, updModel);
        blogClassifyService.blogClassifyUpd(updModel);
        return null;
    }

    public BlogClassifyInfoVo blogClassifyInfo(IdRequest request) {
        if (request.getId() == null) {
            throw new BizException("ID不能为空");
        }
        BlogClassify blogClassify = blogClassifyService.queryBlogClassifyById(request.getId());
        if (blogClassify == null) {
            throw new BizException("不存在");
        }
        BlogClassifyInfoVo infoVo = new BlogClassifyInfoVo();
        BeanUtils.copyProperties(blogClassify, infoVo);
        infoVo.setId(blogClassify.getId().toString());
        return infoVo;
    }

    public Void blogClassifyDel(IdRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            throw new BizException("IDS不能为空");
        }
        // BlogClassify blogClassify = blogClassifyService.queryBlogClassifyById(request.getId());
        // if (blogClassify == null) {
        //     throw new BizException("不存在");
        // }
        blogClassifyService.blogClassifyDel(request.getIds());
        return null;
    }

}
