package com.wjl.site.controller;

import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.service.intf.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 文件接口
 *
 * @author: wjl
 * @date: 2021/11/24 18:09
 * @version: v1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file file
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam(value = "file") MultipartFile file) {
        if (!Objects.isNull(file)) {
            return Result.success(fileService.fileUpload(file));
        }
        return Result.fail(HttpStatus.FAILED.getCode(), "upload failed");
    }

}
