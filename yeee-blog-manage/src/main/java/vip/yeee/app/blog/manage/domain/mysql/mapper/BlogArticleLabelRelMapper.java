package vip.yeee.app.blog.manage.domain.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleLabelRel;
import vip.yeee.app.common.model.dto.BlogArticleLabelDto;

import java.util.List;

/**
 * create by yeee.一页 2023-07-07 10:46:21
 */
public interface BlogArticleLabelRelMapper extends BaseMapper<BlogArticleLabelRel> {

    List<BlogArticleLabelDto> selectLabelByArticleIds(@Param("articleIds") List<Long> articleIds);
}
