package com.wjl.site.service.intf;

import com.wjl.site.model.entity.Video;

import java.util.List;

/**
 * @author: wjl
 * @date: 2021/11/22 16:35
 * @version: v1.0
 */
public interface VideoService {

    List<Video> findAllVideos();

    Video findVideoById(Long id);

}
