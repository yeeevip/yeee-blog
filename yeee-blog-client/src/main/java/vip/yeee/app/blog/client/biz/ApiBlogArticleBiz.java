package vip.yeee.app.blog.client.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.model.dto.BlogArticleDto;
import vip.yeee.app.blog.client.model.request.ApiBlogArticlePageListRequest;
import vip.yeee.app.blog.client.model.vo.ApiBlogArticleDetailVo;
import vip.yeee.app.blog.client.model.vo.ApiBlogArticleListVo;
import vip.yeee.app.blog.client.service.ApiBlogArticleService;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.model.request.PageRequest;
import vip.yeee.memo.base.model.exception.BizException;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Component
public class ApiBlogArticleBiz {

    @Resource
    private ApiBlogArticleService apiBlogArticleService;

    @CachePenetrationProtect(timeout = 3)
    @Cached(cacheType = CacheType.BOTH, expire = 60, localExpire = 31)
    public PageVO<ApiBlogArticleListVo> blogArticlePageList(ApiBlogArticlePageListRequest request) {
        IPage<BlogArticleDto> page = apiBlogArticleService.apiBlogArticlePageList(request);
        PageVO<ApiBlogArticleListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<ApiBlogArticleListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    ApiBlogArticleListVo vo = new ApiBlogArticleListVo();
                    BeanUtils.copyProperties(po, vo);
                    vo.setId(po.getId().toString());
                    ApiBlogArticleListVo.Classify classify = new ApiBlogArticleListVo.Classify();
                    classify.setId(po.getClassifyId().toString());
                    classify.setName(po.getClassifyName());
                    vo.setClassify(classify);
                    return vo;
                })
                .collect(Collectors.toList());
        pageVO.setPages((int) page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setResult(voList);
        return pageVO;
    }

    @CachePenetrationProtect(timeout = 3)
    @Cached(cacheType = CacheType.BOTH, expire = 60, localExpire = 31)
    public ApiBlogArticleDetailVo blogArticleDetail(IdRequest request) {
        BlogArticle blogArticle = apiBlogArticleService.queryBlogArticleById(request.getId());
        if (blogArticle == null) {
            throw new BizException("文章不存在");
        }
        ApiBlogArticleDetailVo vo = new ApiBlogArticleDetailVo();
        BeanUtils.copyProperties(blogArticle, vo);
        vo.setId(blogArticle.getId().toString());
        ApiBlogArticleDetailVo.Classify classify = new ApiBlogArticleDetailVo.Classify();
        vo.setClassify(classify);
        return vo;
    }
}
