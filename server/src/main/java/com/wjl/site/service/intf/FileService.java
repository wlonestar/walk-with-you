package com.wjl.site.service.intf;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: wjl
 * @date: 2021/11/24 18:40
 * @version: v1.0
 */
public interface FileService {

    String fileUpload(MultipartFile file);

}
