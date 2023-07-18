package vip.yeee.app.blog.manage.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* create by yeee.一页 2023-07-17 10:00:34
*/
@Data
public class BlogAccessLogListVo {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("IP")
    private String clientIp;

    @ApiModelProperty("Referer")
    private String referer;

    @ApiModelProperty("User-Agent")
    private String userAgent;

    @ApiModelProperty("访问时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date accessTime;

    @ApiModelProperty("详情描述")
    private String remark;

}
