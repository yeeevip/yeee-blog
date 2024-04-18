package vip.yeee.app.blog.client.model.request;

import lombok.Data;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 9:29
 */
@Data
public class BlogStatsRequest {

    private String subject;

    private String subjectId;

    private String event;

    private String clientIp;

    private String referer;

    private String userAgent;

    private Long timestamp;

}
