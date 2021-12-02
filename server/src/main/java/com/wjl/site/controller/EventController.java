package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.entity.Event;
import com.wjl.site.service.intf.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 事件接口
 *
 * @author: wjl
 * @date: 2021/11/19 18:26
 * @version: v1.0
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * 分页获取事件
     *
     * @param page page
     * @param size size
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<Event> findAllByPage(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        return eventService.findAllEventsByPage(PageRequest.of(page - 1, size));
    }

    /**
     * 获取所有事件
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }

    /**
     * 根据 id 获取事件
     *
     * @param id id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findEventById(@RequestParam Long id) {
        if (!Objects.isNull(eventService.findEventById(id))) {
            return eventService.findEventById(id);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find event");
        }
    }

    /**
     * 添加事件
     *
     * @param event event
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addEvent(@RequestBody Event event) {
        if (eventService.addEvent(event)) {
            return event;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add faile");
        }
    }

    /**
     * 根据 id 更新事件
     *
     * @param id id
     * @param event event
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object updateEvent(@RequestParam Long id, @RequestBody Event event) {
        if (eventService.updateEvent(id, event)) {
            return event;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "update failed");
        }
    }

    /**
     * 删除事件
     *
     * @param id id
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteEvent(@PathVariable("id") Long id) {
        if (eventService.deleteEvent(id)) {
            return id;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "delete failed");
        }
    }

}
