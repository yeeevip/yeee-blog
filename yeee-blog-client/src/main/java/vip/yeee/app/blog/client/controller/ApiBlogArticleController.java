package vip.yeee.app.blog.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import vip.yeee.app.blog.client.biz.ApiBlogArticleBiz;
import vip.yeee.app.blog.client.model.request.ApiBlogArticlePageListRequest;
import vip.yeee.app.blog.client.model.vo.ApiBlogArticleDetailVo;
import vip.yeee.app.blog.client.model.vo.ApiBlogArticleListVo;
import vip.yeee.app.common.model.request.IdRequest;
import vip.yeee.app.common.model.request.PageRequest;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
* create by yeee.一页 2023-06-17 14:06:56
*/
@Api(tags = "API-博客文章")
@RequestMapping("/api/blog/article")
@RestController
public class ApiBlogArticleController {

    @Resource
    private ApiBlogArticleBiz apiBlogArticleBiz;

    @ApiOperation("列表")
    @PostMapping(value = "/page")
    public CommonResult<PageVO<ApiBlogArticleListVo>> blogArticlePageList(@Valid @RequestBody ApiBlogArticlePageListRequest request) {
        return CommonResult.success(apiBlogArticleBiz.blogArticlePageList(request));
    }

    @ApiOperation("文章详情")
    @PostMapping(value = "/detail")
    public CommonResult<ApiBlogArticleDetailVo> blogArticleDetail(@Valid @RequestBody IdRequest request) {
        return CommonResult.success(apiBlogArticleBiz.blogArticleDetail(request));
    }

}
