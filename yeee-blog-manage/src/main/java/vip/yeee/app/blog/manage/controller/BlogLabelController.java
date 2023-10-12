package vip.yeee.app.blog.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.manage.biz.BlogLabelBiz;
import vip.yeee.app.blog.manage.model.request.BlogLabelAddRequest;
import vip.yeee.app.blog.manage.model.vo.BlogLabelListVo;
import vip.yeee.app.blog.manage.model.request.BlogLabelUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogLabelInfoVo;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 14:05:11
*/
@Api(tags = "博客标签")
@RequestMapping("/manage/blog/label")
@RestController
public class BlogLabelController {

    @Resource
    private BlogLabelBiz blogLabelBiz;

    @ApiOperation("列表")
    @PreAuthorize("hasAuthority('blog:label:info')")
    @GetMapping(value = "/page")
    public CommonResult<PageVO<BlogLabelListVo>> blogLabelPageList(String query) {
        return CommonResult.success(blogLabelBiz.blogLabelPageList(query));
    }

    @ApiOperation("创建")
    @PreAuthorize("hasAuthority('blog:label:add')")
    @PostMapping(value = "/add")
    public CommonResult<Void> blogLabelAdd(@RequestBody @Validated BlogLabelAddRequest request) {
        return CommonResult.success(blogLabelBiz.blogLabelAdd(request));
    }

    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('blog:label:upd')")
    @PostMapping(value = "/upd")
    public CommonResult<Void> blogLabelUpd(@RequestBody @Validated BlogLabelUpdRequest request) {
        return CommonResult.success(blogLabelBiz.blogLabelUpd(request));
    }

    @ApiOperation("详情")
    @PreAuthorize("hasAuthority('blog:label:info')")
    @PostMapping(value = "/info")
    public CommonResult<BlogLabelInfoVo> blogLabelInfo(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogLabelBiz.blogLabelInfo(request));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('blog:label:del')")
    @PostMapping(value = "/del")
    public CommonResult<Void> blogLabelDel(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogLabelBiz.blogLabelDel(request));
    }
}
