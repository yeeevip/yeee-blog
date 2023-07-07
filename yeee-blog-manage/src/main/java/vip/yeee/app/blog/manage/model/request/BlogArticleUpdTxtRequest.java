package vip.yeee.app.blog.manage.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Data
public class BlogArticleUpdTxtRequest {

    @ApiModelProperty("ID")
    @NotNull(message = "ID不能为空")
    private Long id;

    @ApiModelProperty("内容")
    private String content;

}
