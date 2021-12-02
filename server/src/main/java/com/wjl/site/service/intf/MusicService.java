package com.wjl.site.service.intf;

import com.wjl.site.model.entity.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 音乐服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:06
 * @version: v1.0
 */
public interface MusicService {

    Page<Music> findAllMusicsByPage(Pageable pageable);

    List<Music> findAllMusics();

    Music findMusicById(Long id);

    boolean addMusic(Music music);

    boolean updateMusic(Long id, Music music);

    boolean deleteMusic(Long id);

}
