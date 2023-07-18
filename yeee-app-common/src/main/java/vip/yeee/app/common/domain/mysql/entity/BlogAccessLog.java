package vip.yeee.app.common.domain.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 博客访问日志
 */
@Data
@TableName("t_blog_access_log")
public class BlogAccessLog {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * IP
     */
    private String clientIp;

    /**
     * Referer
     */
    private String referer;

    /**
     * User-Agent
     */
    private String userAgent;

    /**
     * 地址
     */
    private String clientAddress;

    /**
     * 主体
     */
    private String subject;

    /**
     * 主体ID
     */
    private Long subjectId;

    /**
     * 操作事件
     */
    private String event;

    /**
     * 访问时间
     */
    private Date accessTime;

    /**
     * 详情描述
     */
    private String remark;
}