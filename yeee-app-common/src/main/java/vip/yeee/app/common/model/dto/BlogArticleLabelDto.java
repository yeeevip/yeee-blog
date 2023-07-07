package vip.yeee.app.common.model.dto;

import lombok.Data;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/7 11:18
 */
@Data
public class BlogArticleLabelDto {

    private Long articleId;
    private Long labelId;
    private String labelName;

}
