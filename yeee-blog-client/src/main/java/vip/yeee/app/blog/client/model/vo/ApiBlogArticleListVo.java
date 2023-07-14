package vip.yeee.app.blog.client.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Data
public class ApiBlogArticleListVo {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("分类")
    private Classify classify;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("标题图")
    private String titleImg;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("原创")
    private Integer oriFlag;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    @ApiModelProperty("文章类型:0原文，1外链")
    private Integer type;

    @ApiModelProperty("外链地址")
    private String linkUrl;

    @ApiModelProperty("阅读量")
    private Integer readNum;

    @ApiModelProperty("点赞量")
    private Integer likeNum;

    @ApiModelProperty("说明")
    private String remark;

    @Data
    public static class Classify {

        @ApiModelProperty("分类ID")
        private String id;

        @ApiModelProperty("分类名称")
        private String name;

    }

}
