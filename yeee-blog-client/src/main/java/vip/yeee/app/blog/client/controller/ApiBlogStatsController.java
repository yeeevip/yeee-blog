package vip.yeee.app.blog.client.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.yeee.app.blog.client.constatnt.BlogStatsEnum;
import vip.yeee.app.blog.client.model.request.BlogStatsRequest;
import vip.yeee.app.common.utils.HttpRequestUtils;

import java.util.Arrays;
import java.util.Date;

/**
* create by yeee.一页 2023-07-13 14:06:10
*/
@Api(tags = "API-博客访问统计")
@RequestMapping("/api/blog/stats")
@RestController
public class ApiBlogStatsController {

    private final static Logger log = LoggerFactory.getLogger("YEEE-BLOG-STATS");

    @PostMapping(value = "/")
    public void apiBlogRecordStatsData(BlogStatsRequest request) {

        if (StrUtil.isBlank(request.getSubject()) || StrUtil.isBlank(request.getEvent())) {
            return;
        }

        if (DateUtil.between(DateUtil.date(request.getTimestamp()), new Date(), DateUnit.SECOND) > 30) {
            return;
        }

        if (Arrays.stream(BlogStatsEnum.Subject.values()).noneMatch(s -> s.getCode().equals(request.getSubject()))
                || Arrays.stream(BlogStatsEnum.Event.values()).noneMatch(s -> s.getCode().equals(request.getEvent()))) {
            return;
        }

        request.setClientIp(HttpRequestUtils.getIpAddr(HttpRequestUtils.getHttpServletRequest()));

        log.info(JSON.toJSONString(request));
    }

}
