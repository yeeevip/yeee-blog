package vip.yeee.app.blog.client.handle;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;
import vip.yeee.app.blog.client.service.ApiBlogAccessLogService;
import vip.yeee.app.common.domain.mysql.entity.BlogAccessLog;
import vip.yeee.app.common.service.CommonService;

import javax.annotation.Resource;
import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 11:46
 */
@Slf4j
public abstract class AbstractSinkHandle implements SinkHandle {

    @Resource
    private CommonService commonService;
    @Resource
    private ApiBlogAccessLogService apiBlogAccessLogService;

    public void saveAccessLog(List<BlogStatsRequest> d) {
        try {
            for (List<BlogStatsRequest> list : Lists.partition(d, 50)) {
                List<BlogAccessLog> saveList = Lists.newArrayList();
                for (BlogStatsRequest request : list) {
                    try {
                        BlogAccessLog save = new BlogAccessLog();
                        BeanUtils.copyProperties(request, save);
                        save.setSubjectId(Long.valueOf(StrUtil.emptyToDefault(request.getSubjectId(), "0")));
                        save.setAccessTime(DateUtil.date(request.getTimestamp()));
                        save.setClientAddress(commonService.getCachedAddressByIp(request.getClientIp()));
                        save.setRemark(genAccessLogRemark(save));
                        save.setUserAgent(StrUtil.subWithLength(save.getUserAgent(), 0, 1000));
                        saveList.add(save);
                    } catch (Exception ignored) {

                    }
                }
                apiBlogAccessLogService.saveBatch(saveList);
            }
        } catch (Exception e) {
            log.error("saveAccessLog error", e);
        }
    }

    public abstract String genAccessLogRemark(BlogAccessLog save);
}
