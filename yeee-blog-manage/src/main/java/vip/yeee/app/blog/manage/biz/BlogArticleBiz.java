package vip.yeee.app.blog.manage.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.manage.model.request.BlogArticleAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogArticleUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogArticleInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogArticleListVo;
import vip.yeee.app.blog.manage.service.BlogArticleService;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;
import vip.yeee.memo.base.model.exception.BizException;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Component
public class BlogArticleBiz {

    @Resource
    private BlogArticleService blogArticleService;

    public PageVO<BlogArticleListVo> blogArticlePageList(String query) {
        IPage<BlogArticle> page = blogArticleService.blogArticlePageList(query);
        PageVO<BlogArticleListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<BlogArticleListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    BlogArticleListVo vo = new BlogArticleListVo();
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

    public Void blogArticleAdd(BlogArticleAddRequest request) {
        BlogArticle saveModel = new BlogArticle();
        BeanUtils.copyProperties(request, saveModel);
        blogArticleService.blogArticleAdd(saveModel);
        return null;
    }

    public Void blogArticleUpd(BlogArticleUpdRequest request) {
        BlogArticle blogArticle = blogArticleService.queryBlogArticleById(request.getId());
        if (blogArticle == null) {
            throw new BizException("不存在");
        }
        BlogArticle updModel = new BlogArticle();
        BeanUtils.copyProperties(request, updModel);
        blogArticleService.blogArticleUpd(updModel);
        return null;
    }

    public BlogArticleInfoVo blogArticleInfo(IdRequest request) {
        if (request.getId() == null) {
            throw new BizException("ID不能为空");
        }
        BlogArticle blogArticle = blogArticleService.queryBlogArticleById(request.getId());
        if (blogArticle == null) {
            throw new BizException("不存在");
        }
        BlogArticleInfoVo infoVo = new BlogArticleInfoVo();
        BeanUtils.copyProperties(blogArticle, infoVo);
        infoVo.setId(blogArticle.getId().toString());
        return infoVo;
    }

    public Void blogArticleDel(IdRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            throw new BizException("IDS不能为空");
        }
        // BlogArticle blogArticle = blogArticleService.queryBlogArticleById(request.getId());
        // if (blogArticle == null) {
        //     throw new BizException("不存在");
        // }
        blogArticleService.blogArticleDel(request.getIds());
        return null;
    }

}
