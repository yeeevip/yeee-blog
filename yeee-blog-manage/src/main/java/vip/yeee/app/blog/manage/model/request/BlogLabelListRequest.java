package vip.yeee.app.blog.manage.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* create by yeee.一页 2023-06-17 14:05:11
*/
@Data
public class BlogLabelListRequest {

    @ApiModelProperty("当前页码")
    @NotNull(message = "当前页码不能为空")
    private Integer pageNum;

    @ApiModelProperty("分页大小")
    @NotNull(message = "分页大小不能为空")
    private Integer pageSize;

}
