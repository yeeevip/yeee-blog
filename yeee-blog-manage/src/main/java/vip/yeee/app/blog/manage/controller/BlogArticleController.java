package vip.yeee.app.blog.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.manage.biz.BlogArticleBiz;
import vip.yeee.app.blog.manage.model.request.BlogArticleAddRequest;
import vip.yeee.app.blog.manage.model.request.BlogArticleUpdRequest;
import vip.yeee.app.blog.manage.model.request.BlogArticleUpdTxtRequest;
import vip.yeee.app.blog.manage.model.vo.BlogArticleInfoVo;
import vip.yeee.app.blog.manage.model.vo.BlogArticleListVo;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Api(tags = "博客文章")
@RequestMapping("/manage/blog/article")
@RestController
public class BlogArticleController {

    @Resource
    private BlogArticleBiz blogArticleBiz;

    @ApiOperation("列表")
    @PreAuthorize("hasAuthority('blog:article:info')")
    @GetMapping(value = "/page")
    public CommonResult<PageVO<BlogArticleListVo>> blogArticlePageList(String query) {
        return CommonResult.success(blogArticleBiz.blogArticlePageList(query));
    }

    @ApiOperation("创建")
    @PreAuthorize("hasAuthority('blog:article:add')")
    @PostMapping(value = "/add")
    public CommonResult<Void> blogArticleAdd(@RequestBody @Validated BlogArticleAddRequest request) {
        return CommonResult.success(blogArticleBiz.blogArticleAdd(request));
    }

    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('blog:article:upd')")
    @PostMapping(value = "/upd")
    public CommonResult<Void> blogArticleUpd(@RequestBody @Validated BlogArticleUpdRequest request) {
        return CommonResult.success(blogArticleBiz.blogArticleUpd(request));
    }

    @ApiOperation("编辑文章")
    @PreAuthorize("hasAuthority('blog:article:upd')")
    @PostMapping(value = "/upd-txt")
    public CommonResult<Void> blogArticleUpdTxt(@RequestBody @Validated BlogArticleUpdTxtRequest request) {
        return CommonResult.success(blogArticleBiz.blogArticleUpdTxt(request));
    }

    @ApiOperation("详情")
    @PreAuthorize("hasAuthority('blog:article:info')")
    @PostMapping(value = "/info")
    public CommonResult<BlogArticleInfoVo> blogArticleInfo(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogArticleBiz.blogArticleInfo(request));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('blog:article:del')")
    @PostMapping(value = "/del")
    public CommonResult<Void> blogArticleDel(@RequestBody @Validated IdRequest request) {
        return CommonResult.success(blogArticleBiz.blogArticleDel(request));
    }
}
