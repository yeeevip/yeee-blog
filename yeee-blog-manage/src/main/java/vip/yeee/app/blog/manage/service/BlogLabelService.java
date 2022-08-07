package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogLabelMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogLabel;
import vip.yeee.memo.base.mybatisplus.warpper.MyPageWrapper;

import java.io.Serializable;
import java.util.List;

/**
* create by yeee.一页 2023-06-17 14:05:11
*/
@Service
public class BlogLabelService extends ServiceImpl<BlogLabelMapper, BlogLabel> {

    public IPage<BlogLabel> blogLabelPageList(String query) {
        MyPageWrapper<BlogLabel> pageWrapper = new MyPageWrapper<>(query);
        LambdaQueryWrapper<BlogLabel> queryWrapper = pageWrapper.getLambdaQueryWrapper();
        queryWrapper.eq(BlogLabel::getDeleted, 0);
        return this.page(pageWrapper.getPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogLabelAdd(BlogLabel saveModel) {
        this.save(saveModel);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogLabelUpd(BlogLabel updModel) {
        this.updateById(updModel);
    }

    public void blogLabelDel(List<? extends Serializable> ids) {
        LambdaUpdateWrapper<BlogLabel> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(BlogLabel::getId, ids);
        updateWrapper.set(BlogLabel::getDeleted, 1);
        this.update(updateWrapper);
    }

    public BlogLabel queryBlogLabelById(Serializable id) {
        return this.getById(id);
    }

}
