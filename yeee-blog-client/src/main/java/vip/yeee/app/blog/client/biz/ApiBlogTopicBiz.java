package vip.yeee.app.blog.client.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.model.vo.ApiBlogTopicListVo;
import vip.yeee.app.blog.client.service.ApiBlogTopicService;
import vip.yeee.app.common.domain.mysql.entity.BlogTopic;
import vip.yeee.app.common.model.request.PageRequest;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* create by yeee.一页 2023-06-17 14:06:10
*/
@Component
public class ApiBlogTopicBiz {

    @Resource
    private ApiBlogTopicService apiBlogTopicService;

    @CachePenetrationProtect(timeout = 3)
    @Cached(cacheType = CacheType.BOTH, expire = 60, localExpire = 31)
    public PageVO<ApiBlogTopicListVo> apiBlogTopicPageList(PageRequest request) {
        IPage<BlogTopic> page = apiBlogTopicService.apiBlogTopicPageList(request);
        PageVO<ApiBlogTopicListVo> pageVO = new PageVO<>((int) page.getCurrent(), (int) page.getSize());
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return pageVO;
        }
        List<ApiBlogTopicListVo> voList = page.getRecords()
                .stream()
                .map(po -> {
                    ApiBlogTopicListVo vo = new ApiBlogTopicListVo();
                    BeanUtils.copyProperties(po, vo);
                    vo.setId(po.getId().toString());
                    return vo;
                })
                .collect(Collectors.toList());
        pageVO.setPages((int) page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setResult(voList);
        return pageVO;
    }
}
