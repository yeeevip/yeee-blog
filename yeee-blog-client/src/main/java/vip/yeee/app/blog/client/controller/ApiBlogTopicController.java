package vip.yeee.app.blog.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.client.biz.ApiBlogTopicBiz;
import vip.yeee.app.blog.client.model.vo.ApiBlogTopicListVo;
import vip.yeee.app.common.model.request.PageRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 14:06:10
*/
@Api(tags = "API-博客专题")
@RequestMapping("/api/blog/topic")
@RestController
public class ApiBlogTopicController {

    @Resource
    private ApiBlogTopicBiz apiBlogTopicBiz;

    @ApiOperation("列表")
    @PostMapping(value = "/page")
    public CommonResult<PageVO<ApiBlogTopicListVo>> apiBlogTopicPageList(@Validated @RequestBody PageRequest request) {
        return CommonResult.success(apiBlogTopicBiz.apiBlogTopicPageList(request));
    }

}
