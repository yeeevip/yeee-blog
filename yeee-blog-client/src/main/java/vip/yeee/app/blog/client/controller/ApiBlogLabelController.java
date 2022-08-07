package vip.yeee.app.blog.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.client.biz.ApiBlogLabelBiz;
import vip.yeee.app.blog.client.model.vo.ApiBlogLabelListVo;
import vip.yeee.app.common.model.request.PageRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 14:05:11
*/
@Api(tags = "API-博客标签")
@RequestMapping("/api/blog/label")
@RestController
public class ApiBlogLabelController {

    @Resource
    private ApiBlogLabelBiz apiBlogLabelBiz;

    @ApiOperation("列表")
    @PostMapping(value = "/page")
    public CommonResult<PageVO<ApiBlogLabelListVo>> apiBlogLabelPageList(@Validated @RequestBody PageRequest request) {
        return CommonResult.success(apiBlogLabelBiz.apiBlogLabelPageList(request));
    }

}
