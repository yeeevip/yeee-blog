package vip.yeee.app.blog.client.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* create by yeee.一页 2023-06-17 13:53:44
*/
@Data
public class ApiBlogClassifyListVo {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

}
