package vip.yeee.app.blog.manage.model.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/8/11 10:33
 */
@Data
public class YeeeBlogDetailVO {
    private Integer id;
    private String name;
    private String content;
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private LocalDateTime createTime;
    private String author;
    private String remark;
}
