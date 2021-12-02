package com.wjl.site.repository;

import com.wjl.site.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 事件实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 18:04
 * @version: v1.0
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findEventById(Long id);

}
