package vip.yeee.app.blog.manage.domain.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.yeee.app.common.domain.mysql.entity.BlogLabel;
import vip.yeee.app.common.model.dto.BlogArticleLabelDto;

import java.util.List;

/**
 * create by yeee.一页 2023-06-17 14:05:11
 */
public interface BlogLabelMapper extends BaseMapper<BlogLabel> {

    List<BlogArticleLabelDto> selectLabelByArticleIds(@Param("articleIds") List<Long> articleIds);

}
