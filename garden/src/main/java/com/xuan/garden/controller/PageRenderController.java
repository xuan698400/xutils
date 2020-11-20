package com.xuan.garden.controller;

import com.xuan.garden.common.Result;
import com.xuan.garden.common.ResultBuilder;
import com.xuan.garden.model.request.PageRenderRequest;
import com.xuan.garden.model.vo.PageVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2020/11/19
 */
@RestController
@RequestMapping(value = "/page/")
public class PageRenderController {

    @RequestMapping(value = "render")
    public Result<PageVO> render(PageRenderRequest pageRenderRequest) {
        return ResultBuilder.obtainSuccess();
    }

}
