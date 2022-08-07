package vip.yeee.app.blog.client.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* create by yeee.一页 2023-06-17 14:06:10
*/
@Data
public class ApiBlogTopicListVo {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("封面图")
    private String coverImg;

    @ApiModelProperty("介绍")
    private String remark;

}
