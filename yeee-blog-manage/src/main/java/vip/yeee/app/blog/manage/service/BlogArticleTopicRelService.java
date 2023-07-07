package vip.yeee.app.blog.manage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.yeee.app.blog.manage.domain.mysql.mapper.BlogArticleTopicRelMapper;
import vip.yeee.app.common.domain.mysql.entity.BlogArticleTopicRel;
import vip.yeee.app.common.model.dto.BlogArticleTopicDto;

import java.util.Arrays;
import java.util.List;

/**
* create by yeee.一页 2023-07-07 10:49:40
*/
@Service
public class BlogArticleTopicRelService extends ServiceImpl<BlogArticleTopicRelMapper, BlogArticleTopicRel> {

    public List<BlogArticleTopicDto> selectTopicByArticleIds(List<Long> articleIds) {
        return baseMapper.selectLabelByArticleIds(articleIds);
    }
}
