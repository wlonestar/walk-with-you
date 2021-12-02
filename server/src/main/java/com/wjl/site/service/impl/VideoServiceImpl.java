package com.wjl.site.service.impl;

import com.wjl.site.model.entity.Video;
import com.wjl.site.repository.VideoRepository;
import com.wjl.site.service.intf.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wjl
 * @date: 2021/11/22 16:36
 * @version: v1.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video findVideoById(Long id) {
        return videoRepository.findVideoById(id);
    }

}
