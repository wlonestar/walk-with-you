package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.entity.Music;
import com.wjl.site.service.intf.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 音乐接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:11
 * @version: v1.0
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 分页获取音乐
     *
     * @param page page
     * @param size size
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<Music> findAllByPage(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        return musicService.findAllMusicsByPage(PageRequest.of(page - 1, size, Sort.Direction.DESC, "id"));
    }

    /**
     * 获取所有音乐
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Music> findAllMusic() {
        return musicService.findAllMusics();
    }

    /**
     * 根据 id 获取音乐
     *
     * @param id id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findMusicById(@RequestParam Long id) {
        if (!Objects.isNull(musicService.findMusicById(id))) {
            return musicService.findMusicById(id);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find event");
        }
    }

    /**
     * 添加音乐
     *
     * @param music music
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addMusic(@RequestBody Music music) {
        if (musicService.addMusic(music)) {
            return music;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add faile");
        }
    }

    /**
     * 根据 id 更新音乐
     *
     * @param id id
     * @param music music
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object updateMusic(@RequestParam Long id, @RequestBody Music music) {
        if (musicService.updateMusic(id, music)) {
            return music;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "update failed");
        }
    }

    /**
     * 删除音乐
     *
     * @param id id
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteMusic(@PathVariable("id") Long id) {
        if (musicService.deleteMusic(id)) {
            return id;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "delete failed");
        }
    }

}
