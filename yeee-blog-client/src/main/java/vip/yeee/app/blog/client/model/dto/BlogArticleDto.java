package vip.yeee.app.blog.client.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.yeee.app.common.domain.mysql.entity.BlogArticle;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/8 17:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogArticleDto extends BlogArticle {

    private Long classifyId;

    private String classifyName;

    private Long labelId;

    private Long topicId;

    private String labelCode;
}
