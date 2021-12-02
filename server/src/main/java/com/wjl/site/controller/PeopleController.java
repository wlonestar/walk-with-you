package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.entity.People;
import com.wjl.site.service.intf.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 人物接口
 *
 * @author: wjl
 * @date: 2021/11/21 14:32
 * @version: v1.0
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    /**
     * 分页获取人物
     *
     * @param page page
     * @param size size
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<People> findAllByPage(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        return peopleService.findAllPeoplesByPage(PageRequest.of(page - 1, size, Sort.Direction.DESC, "id"));
    }

    /**
     * 获取所有人物
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<People> findAllPeople() {
        return peopleService.findAllPeoples();
    }

    /**
     * 根据 id 获取人物
     *
     * @param id id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findPeopleById(@RequestParam Long id) {
        if (!Objects.isNull(peopleService.findPeopleById(id))) {
            return peopleService.findPeopleById(id);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find event");
        }
    }

    /**
     * 添加人物
     *
     * @param People People
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addPeople(@RequestBody People People) {
        if (peopleService.addPeople(People)) {
            return People;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add faile");
        }
    }

    /**
     * 根据 id 更新人物
     *
     * @param id id
     * @param People People
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object updatePeople(@RequestParam Long id, @RequestBody People People) {
        if (peopleService.updatePeople(id, People)) {
            return People;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "update failed");
        }
    }

    /**
     * 删除人物
     *
     * @param id id
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deletePeople(@PathVariable("id") Long id) {
        if (peopleService.deletePeople(id)) {
            return id;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "delete failed");
        }
    }

}
