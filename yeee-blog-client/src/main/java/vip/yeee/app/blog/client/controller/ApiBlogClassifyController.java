package vip.yeee.app.blog.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.client.biz.ApiBlogClassifyBiz;
import vip.yeee.app.blog.client.model.vo.ApiBlogClassifyListVo;
import vip.yeee.app.common.model.request.PageRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 13:53:44
*/
@Api(tags = "API-博客分类")
@RequestMapping("/api/blog/classify")
@RestController
public class ApiBlogClassifyController {

    @Resource
    private ApiBlogClassifyBiz apiBlogClassifyBiz;

    @ApiOperation("列表")
    @PostMapping(value = "/page")
    public CommonResult<PageVO<ApiBlogClassifyListVo>> blogArticlePageList(@Validated @RequestBody PageRequest request) {
        return CommonResult.success(apiBlogClassifyBiz.apiBlogClassifyPageList(request));
    }

}
