package vip.yeee.app.blog.client.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.yeee.app.blog.client.domain.mysql.mapper.ApiBlogClassifyMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogClassify;
import vip.yeee.app.common.model.request.PageRequest;

/**
* create by yeee.一页 2023-06-17 13:53:44
*/
@Service
public class ApiBlogClassifyService extends ServiceImpl<ApiBlogClassifyMapper, BlogClassify> {

    public IPage<BlogClassify> apiBlogClassifyPageList(PageRequest request) {
        LambdaQueryWrapper<BlogClassify> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BlogClassify::getDeleted, 0);
        queryWrapper.eq(BlogClassify::getStatus, 1);
        queryWrapper.orderByDesc(BlogClassify::getSort);
        return this.page(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
