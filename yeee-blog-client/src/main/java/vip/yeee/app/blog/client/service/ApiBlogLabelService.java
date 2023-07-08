package vip.yeee.app.blog.client.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.yeee.app.blog.client.domain.mysql.mapper.ApiBlogLabelMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogLabel;
import vip.yeee.app.common.model.request.PageRequest;

/**
* create by yeee.一页 2023-06-17 14:05:11
*/
@Service
public class ApiBlogLabelService extends ServiceImpl<ApiBlogLabelMapper, BlogLabel> {

    public IPage<BlogLabel> apiBlogLabelPageList(PageRequest request) {
        LambdaQueryWrapper<BlogLabel> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BlogLabel::getDeleted, 0);
        queryWrapper.eq(BlogLabel::getStatus, 1);
        queryWrapper.eq(BlogLabel::getType, 1);
        queryWrapper.orderByDesc(BlogLabel::getSort);
        return this.page(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
