package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogClassifyMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogClassify;
import vip.yeee.memo.base.mybatisplus.warpper.MyPageWrapper;

import java.io.Serializable;
import java.util.List;

/**
* create by yeee.一页 2023-06-17 13:53:44
*/
@Service
public class BlogClassifyService extends ServiceImpl<BlogClassifyMapper, BlogClassify> {

    public IPage<BlogClassify> blogClassifyPageList(String query) {
        MyPageWrapper<BlogClassify> pageWrapper = new MyPageWrapper<>(query);
        LambdaQueryWrapper<BlogClassify> queryWrapper = pageWrapper.getLambdaQueryWrapper();
        queryWrapper.eq(BlogClassify::getDeleted, 0);
        return this.page(pageWrapper.getPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogClassifyAdd(BlogClassify saveModel) {
        this.save(saveModel);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void blogClassifyUpd(BlogClassify updModel) {
        this.updateById(updModel);
    }

    public void blogClassifyDel(List<? extends Serializable> ids) {
        LambdaUpdateWrapper<BlogClassify> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(BlogClassify::getId, ids);
        updateWrapper.set(BlogClassify::getDeleted, 1);
        this.update(updateWrapper);
    }

    public BlogClassify queryBlogClassifyById(Serializable id) {
        return this.getById(id);
    }

}
