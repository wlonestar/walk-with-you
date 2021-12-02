package com.wjl.site.repository;

import com.wjl.site.model.entity.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 音乐实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 18:05
 * @version: v1.0
 */
@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    Music findMusicById(Long id);

}
