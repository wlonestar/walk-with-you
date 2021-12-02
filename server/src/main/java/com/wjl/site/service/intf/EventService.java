package com.wjl.site.service.intf;

import com.wjl.site.model.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 事件服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 18:16
 * @version: v1.0
 */
public interface EventService {

    Page<Event> findAllEventsByPage(Pageable pageable);

    List<Event> findAllEvents();

    Event findEventById(Long id);

    boolean addEvent(Event event);

    boolean updateEvent(Long id, Event event);

    boolean deleteEvent(Long id);

}
