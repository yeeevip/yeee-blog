package vip.yeee.app.blog.client.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.yeee.app.common.model.request.PageRequest;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/8 16:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiBlogArticlePageListRequest extends PageRequest {

    @ApiModelProperty("分类ID")
    private Long classifyId;

    @ApiModelProperty("专题ID")
    private Long topicId;

    @ApiModelProperty("标签ID")
    private Long labelId;

    @ApiModelProperty("标签类型：1-普通标签，2-业务标签")
    private String labelCode;

}
