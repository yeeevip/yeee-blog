package vip.yeee.app.blog.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import vip.yeee.app.blog.client.domain.mysql.mapper.ApiBlogArticleMapper;
import vip.yeee.app.blog.client.model.dto.BlogArticleDto;
import vip.yeee.app.blog.client.model.request.ApiBlogArticlePageListRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;
import vip.yeee.app.common.model.request.PageRequest;

import java.io.Serializable;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Service
public class ApiBlogArticleService extends ServiceImpl<ApiBlogArticleMapper, BlogArticle> {

    public BlogArticle queryBlogArticleById(Serializable id) {
        return this.getById(id);
    }

    public IPage<BlogArticleDto> apiBlogArticlePageList(ApiBlogArticlePageListRequest request) {
        BlogArticleDto query = new BlogArticleDto();
        query.setClassifyId(request.getClassifyId());
        query.setTopicId(request.getTopicId());
        query.setLabelId(request.getLabelId());
        query.setLabelCode(request.getLabelCode());
        return baseMapper.apiBlogArticlePageList(new Page<>(request.getPageNum(), request.getPageSize()), query);
    }

    public void incrBlogReadNum(String blogId, int delta) {
        baseMapper.incrBlogReadNum(Long.valueOf(blogId), delta);
    }

    public void incrBlogLikeNum(String blogId, int delta) {
        baseMapper.incrBlogLikeNum(Long.valueOf(blogId), delta);
    }
}
