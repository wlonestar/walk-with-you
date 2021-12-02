package com.wjl.site.repository;

import com.wjl.site.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: wjl
 * @date: 2021/11/22 16:35
 * @version: v1.0
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findVideoById(Long id);

}
