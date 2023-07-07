package vip.yeee.app.blog.manage.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.manage.model.request.BlogArticleAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogArticleUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogArticleInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogArticleListVo;
import vip.yeee.app.blog.manage.service.BlogArticleLabelRelService;
import vip.yeee.app.blog.manage.service.BlogArticleService;
import vip.yeee.app.blog.manage.service.BlogArticleTopicRelService;
import vip.yeee.app.blog.manage.service.BlogClassifyService;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleLabelRel;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleTopicRel;
import vip.yeee.app.common.domain.mysql.entity.BlogClassify;
import vip.yeee.app.common.model.dto.BlogArticleLabelDto;
import vip.yeee.app.common.model.dto.BlogArticleTopicDto;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;
import vip.yeee.memo.base.model.exception.BizException;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Component
public class BlogArticleBiz {

    @Resource
    private BlogArticleService blogArticleService;
    @Resource
    private BlogClassifyService blogClassifyService;
    @Resource
    private BlogArticleLabelRelService blogArticleLabelRelService;
    @Resource
    private BlogArticleTopicRelService blogArticleTopicRelService;

    public PageVO<BlogArticleListVo> blogArticlePageList(String query) {

        IPage<BlogArticle> page = blogArticleService.blogArticlePageList(query);
        PageVO<BlogArticleListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }

        List<Long> classifyIds = page.getRecords()
                .stream()
                .map(BlogArticle::getClassifyId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Map<Long, BlogClassify> blogClassifyMap = blogClassifyService.listByIds(classifyIds)
                .stream()
                .collect(Collectors.toMap(BlogClassify::getId, Function.identity()));

        List<Long> articleIds = page.getRecords()
                .stream()
                .map(BlogArticle::getId)
                .collect(Collectors.toList());
        Map<Long, List<BlogArticleLabelDto>> articleLabelMap = blogArticleLabelRelService
                .selectLabelByArticleIds(articleIds)
                .stream()
                .collect(Collectors.groupingBy(BlogArticleLabelDto::getArticleId));

        Map<Long, List<BlogArticleTopicDto>> articleTopicMap = blogArticleTopicRelService
                .selectTopicByArticleIds(articleIds)
                .stream()
                .collect(Collectors.groupingBy(BlogArticleTopicDto::getArticleId));

        List<BlogArticleListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    BlogArticleListVo vo = new BlogArticleListVo();
                    BeanUtils.copyProperties(po, vo);
                    vo.setId(po.getId().toString());
                    vo.setClassifyId(po.getClassifyId().toString());
                    BlogClassify classify = blogClassifyMap.getOrDefault(po.getClassifyId(), new BlogClassify());
                    vo.setClassifyName(classify.getName());
                    List<BlogArticleLabelDto> labelList = articleLabelMap.getOrDefault(po.getId(), Collections.emptyList());
                    vo.setLabelList(labelList.stream().map(BlogArticleLabelDto::getLabelName).collect(Collectors.toList()));
                    List<BlogArticleTopicDto> topicList = articleTopicMap.getOrDefault(po.getId(), Collections.emptyList());
                    vo.setTopicList(topicList.stream().map(BlogArticleTopicDto::getTopicName).collect(Collectors.toList()));
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
        List<BlogArticleLabelRel> labelRelList = this.genBlogLabelRelList(request.getLabelIds());
        List<BlogArticleTopicRel> topicRelList = this.genBlogTopicRelList(request.getTopicIds());
        blogArticleService.blogArticleAdd(saveModel, labelRelList, topicRelList);
        return null;
    }

    public Void blogArticleUpd(BlogArticleUpdRequest request) {
        BlogArticle blogArticle = blogArticleService.queryBlogArticleById(request.getId());
        if (blogArticle == null) {
            throw new BizException("不存在");
        }
        BlogArticle updModel = new BlogArticle();
        BeanUtils.copyProperties(request, updModel);
        List<BlogArticleLabelRel> labelRelList = this.genBlogLabelRelList(request.getLabelIds());
        List<BlogArticleTopicRel> topicRelList = this.genBlogTopicRelList(request.getTopicIds());
        blogArticleService.blogArticleUpd(updModel, labelRelList, topicRelList);
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
        List<BlogArticleLabelDto> labelList = blogArticleLabelRelService.selectLabelByArticleIds(Collections.singletonList(blogArticle.getId()));
        List<BlogArticleTopicDto> topicList = blogArticleTopicRelService.selectTopicByArticleIds(Collections.singletonList(blogArticle.getId()));
        BlogArticleInfoVo infoVo = new BlogArticleInfoVo();
        BeanUtils.copyProperties(blogArticle, infoVo);
        infoVo.setId(blogArticle.getId().toString());
        infoVo.setClassifyId(blogArticle.getClassifyId().toString());
        infoVo.setLabelIds(labelList.stream().map(BlogArticleLabelDto::getLabelId).map(String::valueOf).collect(Collectors.toList()));
        infoVo.setTopicIds(topicList.stream().map(BlogArticleTopicDto::getTopicId).map(String::valueOf).collect(Collectors.toList()));
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

    private List<BlogArticleLabelRel> genBlogLabelRelList(List<Long> labelIds) {
        if (CollectionUtil.isNotEmpty(labelIds)) {
            return labelIds.stream()
                    .map(labelId -> {
                        BlogArticleLabelRel articleLabelRel = new BlogArticleLabelRel();
                        articleLabelRel.setLabelId(labelId);
                        return articleLabelRel;
                    }).collect(Collectors.toList());
        }
        return null;
    }

    private List<BlogArticleTopicRel> genBlogTopicRelList(List<Long> topicIds) {
        if (CollectionUtil.isNotEmpty(topicIds)) {
            return topicIds.stream()
                    .map(topicId -> {
                        BlogArticleTopicRel articleTopicRel = new BlogArticleTopicRel();
                        articleTopicRel.setTopicId(topicId);
                        return articleTopicRel;
                    }).collect(Collectors.toList());
        }
        return null;
    }

}
