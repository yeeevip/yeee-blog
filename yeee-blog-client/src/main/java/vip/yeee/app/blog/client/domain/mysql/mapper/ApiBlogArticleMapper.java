package vip.yeee.app.blog.client.domain.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import vip.yeee.app.blog.client.model.dto.BlogArticleDto;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;

/**
 * create by yeee.一页 2023-06-17 14:06:56
 */
public interface ApiBlogArticleMapper extends BaseMapper<BlogArticle> {

    IPage<BlogArticleDto> apiBlogArticlePageList(Page<BlogArticle> page, @Param("query") BlogArticleDto query);

    void incrBlogReadNum(@Param("blogId") Long blogId, @Param("delta") int delta);

    void incrBlogLikeNum(@Param("blogId") Long blogId, @Param("delta") int delta);
}
