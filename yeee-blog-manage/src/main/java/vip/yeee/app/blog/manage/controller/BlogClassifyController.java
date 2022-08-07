package vip.yeee.app.blog.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.manage.biz.BlogClassifyBiz;
import vip.yeee.app.blog.manage.model.vo.BlogClassifyInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogClassifyListVo;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.blog.manage.model.request.BlogClassifyAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogClassifyUpdRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 13:53:44
*/
@Api(tags = "博客分类")
@RequestMapping("/manage/blog/classify")
@RestController
public class BlogClassifyController {

    @Resource
    private BlogClassifyBiz blogClassifyBiz;

    @ApiOperation("列表")
    @GetMapping(value = "/page")
    public CommonResult<PageVO<BlogClassifyListVo>> blogClassifyPageList(String query) {
        return CommonResult.success(blogClassifyBiz.blogClassifyPageList(query));
    }

    @ApiOperation("创建")
    @PostMapping(value = "/add")
    public CommonResult<Void> blogClassifyAdd(@RequestBody @Validated BlogClassifyAddRequest request) {
        return CommonResult.success(blogClassifyBiz.blogClassifyAdd(request));
    }

    @ApiOperation("修改")
    @PostMapping(value = "/upd")
    public CommonResult<Void> blogClassifyUpd(@RequestBody @Validated BlogClassifyUpdRequest request) {
        return CommonResult.success(blogClassifyBiz.blogClassifyUpd(request));
    }

    @ApiOperation("详情")
    @PostMapping(value = "/info")
    public CommonResult<BlogClassifyInfoVo> blogClassifyInfo(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogClassifyBiz.blogClassifyInfo(request));
    }

    @ApiOperation("删除")
    @PostMapping(value = "/del")
    public CommonResult<Void> blogClassifyDel(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogClassifyBiz.blogClassifyDel(request));
    }
}
