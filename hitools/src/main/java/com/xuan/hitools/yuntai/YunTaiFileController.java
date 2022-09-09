package com.xuan.hitools.yuntai;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.xuan.hitools.HiToolsResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2022/9/7
 */
@RestController
@RequestMapping("/hitools/yuntai/")
public class YunTaiFileController {

    @Resource
    private YunTaiDBHelper yunTaiDBHelper;

    @RequestMapping(value = "/getFiles")
    public HiToolsResult<List<YunTaiFile>> getFiles(Long parentId) {
        HiToolsResult<List<YunTaiFile>> result = new HiToolsResult<>();
        result.setSuccess(false);
        result.setData(yunTaiDBHelper.getFiles(parentId));
        return result;
    }

    @RequestMapping(value = "createFile")
    public HiToolsResult<Void> createFile(String name, String fileType) {
        HiToolsResult<Void> result = new HiToolsResult<>();
        result.setSuccess(false);
        return result;
    }

}
