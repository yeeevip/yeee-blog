package vip.yeee.app.blog.manage.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Data
public class BlogArticleAddRequest {

    @ApiModelProperty("分类ID")
    private Long classifyId;

    @ApiModelProperty("标签IDS")
    private List<Long> labelIds;

    @ApiModelProperty("专题IDS")
    private List<Long> topicIds;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("标题图")
    private String titleImg;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("是否原创：1-是，0-否")
    private Integer oriFlag;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("发布状态：1-已发布，0-未发布")
    private Integer status;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    @ApiModelProperty("文章类型:0原文，1外链")
    private Integer type;

    @ApiModelProperty("外链地址")
    private String linkUrl;

    @ApiModelProperty("说明")
    private String remark;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("内容")
    private String content;

}
