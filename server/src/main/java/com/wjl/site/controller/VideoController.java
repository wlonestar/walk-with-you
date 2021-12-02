package com.wjl.site.controller;

import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.entity.Video;
import com.wjl.site.service.intf.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 视频接口
 *
 * @author: wjl
 * @date: 2021/11/22 16:37
 * @version: v1.0
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 获取所有视频
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Video> findAllVideos() {
        return videoService.findAllVideos();
    }

    /**
     * 根据 id 获取视频
     *
     * @param id id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findById(@RequestParam Long id) {
        if (!Objects.isNull(videoService.findVideoById(id))) {
            return videoService.findVideoById(id);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find video");
        }
    }

}
