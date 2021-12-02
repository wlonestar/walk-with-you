package com.wjl.site.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.wjl.site.common.constants.GlobalConstants;
import com.wjl.site.service.intf.FileService;
import com.wjl.site.util.CosClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: wjl
 * @date: 2021/11/24 18:41
 * @version: v1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String fileUpload(MultipartFile file) {
        COSClient cosClient = CosClientUtil.createClient();
        // convert Multipartfile to File
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String eName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID() + eName;
        File localFile;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            String key = GlobalConstants.FOLDER + newFilename;
            String bucketName = GlobalConstants.BUCKET;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult.getETag());
            return "https://" + GlobalConstants.BUCKET + ".cos." + GlobalConstants.REGION + ".myqcloud.com/" + key;
        } catch (IOException e) {
            e.printStackTrace();
            return "upload failed";
        } finally {
            cosClient.shutdown();
        }
    }

}
