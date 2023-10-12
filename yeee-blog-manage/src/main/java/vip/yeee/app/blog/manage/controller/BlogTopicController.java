package vip.yeee.app.blog.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.manage.biz.BlogTopicBiz;
import vip.yeee.app.blog.manage.model.request.BlogTopicAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogTopicUpdRequest;
import vip.yeee.app.blog.manage.model.vo.BlogTopicInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogTopicListVo;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 14:06:10
*/
@Api(tags = "博客专题")
@RequestMapping("/manage/blog/topic")
@RestController
public class BlogTopicController {

    @Resource
    private BlogTopicBiz blogTopicBiz;

    @ApiOperation("列表")
    @PreAuthorize("hasAuthority('blog:topic:info')")
    @GetMapping(value = "/page")
    public CommonResult<PageVO<BlogTopicListVo>> blogTopicPageList(String query) {
        return CommonResult.success(blogTopicBiz.blogTopicPageList(query));
    }

    @ApiOperation("创建")
    @PreAuthorize("hasAuthority('blog:topic:add')")
    @PostMapping(value = "/add")
    public CommonResult<Void> blogTopicAdd(@RequestBody @Validated BlogTopicAddRequest request) {
        return CommonResult.success(blogTopicBiz.blogTopicAdd(request));
    }

    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('blog:topic:upd')")
    @PostMapping(value = "/upd")
    public CommonResult<Void> blogTopicUpd(@RequestBody @Validated BlogTopicUpdRequest request) {
        return CommonResult.success(blogTopicBiz.blogTopicUpd(request));
    }

    @ApiOperation("详情")
    @PreAuthorize("hasAuthority('blog:topic:info')")
    @PostMapping(value = "/info")
    public CommonResult<BlogTopicInfoVo> blogTopicInfo(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogTopicBiz.blogTopicInfo(request));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('blog:topic:del')")
    @PostMapping(value = "/del")
    public CommonResult<Void> blogTopicDel(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogTopicBiz.blogTopicDel(request));
    }
}
