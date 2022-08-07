package vip.yeee.app.blog.client.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.yeee.app.blog.client.domain.mysql.mapper.ApiBlogTopicMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogTopic;
import vip.yeee.app.common.model.request.PageRequest;

/**
* create by yeee.一页 2023-06-17 14:06:09
*/
@Service
public class ApiBlogTopicService extends ServiceImpl<ApiBlogTopicMapper, BlogTopic> {

    public IPage<BlogTopic> apiBlogTopicPageList(PageRequest request) {
        LambdaQueryWrapper<BlogTopic> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BlogTopic::getDeleted, 0);
        queryWrapper.eq(BlogTopic::getStatus, 1);
        queryWrapper.orderByDesc(BlogTopic::getSort);
        return this.page(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
