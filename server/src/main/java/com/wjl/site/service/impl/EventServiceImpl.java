package com.wjl.site.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.wjl.site.component.QueryComponent;
import com.wjl.site.model.entity.Event;
import com.wjl.site.model.entity.QEvent;
import com.wjl.site.repository.EventRepository;
import com.wjl.site.service.intf.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 事件服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 18:18
 * @version: v1.0
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private QueryComponent queryComponent;

    @Autowired
    private EventRepository eventRepository;

    QEvent qEvent = QEvent.event;

    @Override
    public Page<Event> findAllEventsByPage(Pageable pageable) {
        QueryResults<Tuple> queryResults = queryComponent.queryFactory()
                .select(
                        qEvent.id,
                        qEvent.name,
                        qEvent.time,
                        qEvent.location,
                        qEvent.introduction
                )
                .from(qEvent)
                .orderBy(qEvent.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Tuple> tuples = queryResults.getResults();
        List<Event> events = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Event event = new Event();
            event.setId(tuple.get(qEvent.id));
            event.setName(tuple.get(qEvent.name));
            event.setTime(tuple.get(qEvent.time));
            event.setLocation(tuple.get(qEvent.location));
            event.setIntroduction(tuple.get(qEvent.introduction));
            events.add(event);
        }
        return new PageImpl<>(events, pageable, queryResults.getTotal());
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event findEventById(Long id) {
        return eventRepository.findEventById(id);
    }

    @Override
    public boolean addEvent(Event event) {
        if (!Objects.isNull(event)) {
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEvent(Long id, Event event) {
        if (!Objects.isNull(event)) {
            Event event1 = findEventById(id);
            if (!Objects.isNull(event1)) {
                BeanUtils.copyProperties(event, event1, "id");
                eventRepository.save(event1);
                eventRepository.flush();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteEvent(Long id) {
        if (!Objects.isNull(findEventById(id))) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
