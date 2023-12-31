package vip.yeee.app.common.domain.mysql.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 博客文章
 */
@Data
@TableName("t_blog_article")
public class BlogArticle {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类ID
     */
    private Long classifyId;

    /**
     * 标题
     */
    private String title;

    /**
     * 标题图
     */
    private String titleImg;

    /**
     * 作者
     */
    private String author;

    /**
     * 是否原创：1-是，0-否
     */
    private Integer oriFlag;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 发布状态：1-已发布，0-未发布
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 文章类型:0原文，1外链
     */
    private Integer type;

    /**
     * 外链地址
     */
    private String linkUrl;

    /**
     * 阅读量
     */
    private Integer readNum;

    /**
     * 点赞量
     */
    private Integer likeNum;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 内容
     */
    private String content;
}