package vip.yeee.app.blog.client.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vip.yeee.app.blog.client.handle.SinkHandle;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;
import vip.yeee.memo.base.redis.kit.RedisKit;
import vip.yeee.memo.base.web.utils.SpringContextUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 11:17
 */
@Slf4j
@Component
public class BlogStatsJob {

    @Resource
    private RedisKit redisKit;

    @Scheduled(fixedRate = 20 * 1000)
    public void statsAccessLog() {
        synchronized (this) {
            String cacheKey = "BLOG:STATS:LOG_LINE";
            String value = redisKit.getValue(cacheKey);

            String filePath = "logs/log_blog_stats.log";
            int startLine = StrUtil.isBlank(value) ? 1 : Integer.parseInt(value);

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                for (int i = 1; i < startLine; i++) {
                    reader.readLine();
                }

                List<BlogStatsRequest> lines = Lists.newArrayList();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(JSONObject.parseObject(line, BlogStatsRequest.class));
                    startLine++;
                }
                if (CollectionUtil.isEmpty(lines)) {
                    return;
                }
                this.sinkLines(lines);
                redisKit.cacheValue(cacheKey, String.valueOf(startLine)
                        , DateUtil.between(new Date(), DateUtil.endOfDay(new Date()), DateUnit.SECOND));
            } catch (IOException e) {
                log.error("BlogStatsJob error", e);
            }
        }
    }

    private void sinkLines(List<BlogStatsRequest> lines) {
        if (CollectionUtil.isEmpty(lines)) {
            return;
        }
        Map<String, List<BlogStatsRequest>> subjectMap = lines.stream().collect(Collectors.groupingBy(BlogStatsRequest::getSubject));
        subjectMap.forEach((subject, sGroup) -> {
            Map<String, List<BlogStatsRequest>> eventMap = sGroup.stream().collect(Collectors.groupingBy(BlogStatsRequest::getEvent));
            eventMap.forEach((event, eGroup) -> {
                SinkHandle sinkHandle = (SinkHandle) SpringContextUtils.getBean(StrUtil.toCamelCase(subject + "_" + event) + "SinkHandle");
                sinkHandle.sink(eGroup);
            });
        });
    }
}
