package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogTopicMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogTopic;
import vip.yeee.memo.base.mybatisplus.warpper.MyPageWrapper;

import java.io.Serializable;
import java.util.List;

/**
* create by yeee.一页 2023-06-17 14:06:09
*/
@Service
public class BlogTopicService extends ServiceImpl<BlogTopicMapper, BlogTopic> {

    public IPage<BlogTopic> blogTopicPageList(String query) {
        MyPageWrapper<BlogTopic> pageWrapper = new MyPageWrapper<>(query);
        LambdaQueryWrapper<BlogTopic> queryWrapper = pageWrapper.getLambdaQueryWrapper();
        queryWrapper.eq(BlogTopic::getDeleted, 0);
        return this.page(pageWrapper.getPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogTopicAdd(BlogTopic saveModel) {
        this.save(saveModel);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogTopicUpd(BlogTopic updModel) {
        this.updateById(updModel);
    }

    public void blogTopicDel(List<? extends Serializable> ids) {
        LambdaUpdateWrapper<BlogTopic> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(BlogTopic::getId, ids);
        updateWrapper.set(BlogTopic::getDeleted, 1);
        this.update(updateWrapper);
    }

    public BlogTopic queryBlogTopicById(Serializable id) {
        return this.getById(id);
    }

}
