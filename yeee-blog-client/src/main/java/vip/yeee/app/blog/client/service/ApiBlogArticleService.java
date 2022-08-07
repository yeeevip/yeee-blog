package vip.yeee.app.blog.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import vip.yeee.app.blog.client.domain.mysql.mapper.ApiBlogArticleMapper;
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

    public IPage<BlogArticle> apiBlogArticlePageList(PageRequest request) {
        BlogArticle query = new BlogArticle();
        return baseMapper.apiBlogArticlePageList(new Page<>(request.getPageNum(), request.getPageSize()), query);
    }
}
