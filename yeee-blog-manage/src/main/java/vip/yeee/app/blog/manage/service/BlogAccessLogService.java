package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogAccessLogMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogAccessLog;
import vip.yeee.memo.base.mybatisplus.warpper.MyPageWrapper;

/**
* create by yeee.一页 2023-07-17 10:00:34
*/
@Service
public class BlogAccessLogService extends ServiceImpl<BlogAccessLogMapper, BlogAccessLog> {

    public IPage<BlogAccessLog> blogAccessLogPageList(String query) {
        MyPageWrapper<BlogAccessLog> pageWrapper = new MyPageWrapper<>(query);
        return this.page(pageWrapper.getPage(), pageWrapper.getQueryWrapper());
    }
}
