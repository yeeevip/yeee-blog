package vip.yeee.app.blog.client.handle;

import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 11:49
 */
@Component
public class IndexReadSinkHandle implements SinkHandle {
    @Override
    public void sink(List<BlogStatsRequest> data) {

    }
}
