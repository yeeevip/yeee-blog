package vip.yeee.app.blog.client.handle;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;
import vip.yeee.app.common.domain.mysql.entity.BlogAccessLog;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 11:49
 */
@Component
public class IndexReadSinkHandle extends AbstractSinkHandle {
    @Override
    public void sink(List<BlogStatsRequest> data) {
        ThreadUtil.execAsync(() -> this.saveAccessLog(data));
    }

    @Override
    public String genAccessLogRemark(BlogAccessLog save) {
        return StrUtil.format("用户{}[{}]‘访问’了‘首页’", save.getClientIp(), save.getClientAddress());
    }
}
