package vip.yeee.app.blog.client.handle;

import vip.yeee.app.blog.client.model.request.BlogStatsRequest;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 11:46
 */
public interface SinkHandle {
    void sink(List<BlogStatsRequest> data);
}
