package vip.yeee.app.blog.manage.domain.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleTopicRel;
import vip.yeee.app.common.model.dto.BlogArticleTopicDto;

import java.util.List;

/**
 * create by yeee.一页 2023-07-07 10:49:40
 */
public interface BlogArticleTopicRelMapper extends BaseMapper<BlogArticleTopicRel> {

    List<BlogArticleTopicDto> selectLabelByArticleIds(@Param("articleIds") List<Long> articleIds);
}
