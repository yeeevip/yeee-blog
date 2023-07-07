package vip.yeee.app.blog.manage.service;

import cn.hutool.core.collection.CollectionUtil;
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
import vip.yeee.app.common.domain.mysql.entity.BlogArticleLabelRel;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleTopicRel;
import vip.yeee.memo.base.mybatisplus.warpper.MyPageWrapper;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Service
public class BlogArticleService extends ServiceImpl<BlogArticleMapper, BlogArticle> {

    @Resource
    private BlogArticleLabelRelService blogArticleLabelRelService;
    @Resource
    private BlogArticleTopicRelService blogArticleTopicRelService;

    public IPage<BlogArticle> blogArticlePageList(String query) {
        MyPageWrapper<BlogArticle> pageWrapper = new MyPageWrapper<>(query);
        LambdaQueryWrapper<BlogArticle> queryWrapper = pageWrapper.getLambdaQueryWrapper();
        queryWrapper.eq(BlogArticle::getDeleted, 0);
        return this.page(pageWrapper.getPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogArticleAdd(BlogArticle saveModel, List<BlogArticleLabelRel> labelRelList, List<BlogArticleTopicRel> topicRelList) {
        this.save(saveModel);
        if (CollectionUtil.isNotEmpty(labelRelList)) {
            labelRelList.forEach(label -> label.setArticleId(saveModel.getId()));
            blogArticleLabelRelService.saveBatch(labelRelList);
        }
        if (CollectionUtil.isNotEmpty(topicRelList)) {
            topicRelList.forEach(topic -> topic.setArticleId(saveModel.getId()));
            blogArticleTopicRelService.saveBatch(topicRelList);
        }
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogArticleUpd(BlogArticle updModel, List<BlogArticleLabelRel> labelRelList, List<BlogArticleTopicRel> topicRelList) {
        this.updateById(updModel);
        if (CollectionUtil.isNotEmpty(labelRelList)) {
            LambdaUpdateWrapper<BlogArticleLabelRel> delLabelRelWrapper = Wrappers.lambdaUpdate();
            delLabelRelWrapper.eq(BlogArticleLabelRel::getArticleId, updModel.getId());
            blogArticleLabelRelService.remove(delLabelRelWrapper);
            labelRelList.forEach(label -> label.setArticleId(updModel.getId()));
            blogArticleLabelRelService.saveBatch(labelRelList);
        }
        if (CollectionUtil.isNotEmpty(topicRelList)) {
            LambdaUpdateWrapper<BlogArticleTopicRel> delTopicRelWrapper = Wrappers.lambdaUpdate();
            delTopicRelWrapper.eq(BlogArticleTopicRel::getArticleId, updModel.getId());
            blogArticleTopicRelService.remove(delTopicRelWrapper);
            topicRelList.forEach(topic -> topic.setArticleId(updModel.getId()));
            blogArticleTopicRelService.saveBatch(topicRelList);
        }
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
