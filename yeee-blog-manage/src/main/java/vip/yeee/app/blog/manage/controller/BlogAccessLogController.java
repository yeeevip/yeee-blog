package vip.yeee.app.blog.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.yeee.app.blog.manage.biz.BlogAccessLogBiz;
import vip.yeee.app.blog.manage.model.vo.BlogAccessLogListVo;
import vip.yeee.memo.base.model.rest.CommonResult;
import vip.yeee.memo.base.model.vo.PageVO;

import javax.annotation.Resource;

/**
* create by yeee.一页 2023-07-17 10:00:34
*/
@Api(tags = "博客访问日志")
@RequestMapping("/manage/blog/accessLog")
@RestController
public class BlogAccessLogController {

    @Resource
    private BlogAccessLogBiz blogAccessLogBiz;

    @ApiOperation("列表")
    @PreAuthorize("hasAuthority('blog:accessLog:info')")
    @GetMapping(value = "/page")
    public CommonResult<PageVO<BlogAccessLogListVo>> blogAccessLogPageList(String query) {
        return CommonResult.success(blogAccessLogBiz.blogAccessLogPageList(query));
    }
}
