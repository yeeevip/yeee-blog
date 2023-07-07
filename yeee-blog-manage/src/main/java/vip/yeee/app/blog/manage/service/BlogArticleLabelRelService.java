package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogArticleLabelRelMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleLabelRel;
import vip.yeee.app.common.model.dto.BlogArticleLabelDto;

import java.util.List;

/**
* create by yeee.一页 2023-07-07 10:46:21
*/
@Service
public class BlogArticleLabelRelService extends ServiceImpl<BlogArticleLabelRelMapper, BlogArticleLabelRel> {

    public List<BlogArticleLabelDto> selectLabelByArticleIds(List<Long> articleIds) {
        return baseMapper.selectLabelByArticleIds(articleIds);
    }
}
