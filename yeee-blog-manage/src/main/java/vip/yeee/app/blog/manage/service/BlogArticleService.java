package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogArticleMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;
import vip.yeee.memo.base.mybatisplus.warpper.MyPageWrapper;

import java.io.Serializable;
import java.util.List;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Service
public class BlogArticleService extends ServiceImpl<BlogArticleMapper, BlogArticle> {

    public IPage<BlogArticle> blogArticlePageList(String query) {
        MyPageWrapper<BlogArticle> pageWrapper = new MyPageWrapper<>(query);
        LambdaQueryWrapper<BlogArticle> queryWrapper = pageWrapper.getLambdaQueryWrapper();
        queryWrapper.eq(BlogArticle::getDeleted, 0);
        return this.page(pageWrapper.getPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogArticleAdd(BlogArticle saveModel) {
        this.save(saveModel);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogArticleUpd(BlogArticle updModel) {
        this.updateById(updModel);
    }

    public void blogArticleDel(List<? extends Serializable> ids) {
        LambdaUpdateWrapper<BlogArticle> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(BlogArticle::getId, ids);
        updateWrapper.set(BlogArticle::getDeleted, 1);
        this.update(updateWrapper);
    }

    public BlogArticle queryBlogArticleById(Serializable id) {
        return this.getById(id);
    }

}
