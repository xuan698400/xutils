package com.xuan.hitools.hiweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuan
 * @since 2022/8/31
 */
@Controller
@RequestMapping("hitools/hiweb/")
public class HiWebController {

    @RequestMapping("page")
    public void page(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<p style=\"color:red;\">dddd中文</p>");
        writer.close();
    }

}
