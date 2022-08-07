package vip.yeee.app.blog.manage.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* create by yeee.一页 2023-06-17 14:06:10
*/
@Data
public class BlogTopicAddRequest {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("封面图")
    private String coverImg;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态：1-启用，0-禁用")
    private Integer status;

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

}
