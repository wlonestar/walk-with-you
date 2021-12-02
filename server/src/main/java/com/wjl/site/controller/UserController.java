package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.dto.UserRoleDto;
import com.wjl.site.model.entity.User;
import com.wjl.site.service.intf.FileService;
import com.wjl.site.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * 用户接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:48
 * @version: v1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    /**
     * 获取所有用户
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * 根据 id 获取用户
     *
     * @param id id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findUserById(@RequestParam Long id) {
        if (!Objects.isNull(userService.findUserById(id))) {
            return userService.findUserById(id);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find user");
        }
    }

    /**
     * 根据用户名获取用户
     *
     * @param username username
     */
    @SaCheckLogin
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public Object findUserByUsername(@RequestParam String username) {
        if (!Objects.isNull(userService.findUserByUsername(username))) {
            return userService.findUserByUsername(username);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find user");
        }
    }

    /**
     * 获取所有用户和角色
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public List<UserRoleDto> findAllUserAndRole() {
        return userService.findAllUserRole();
    }

    /**
     * 添加用户
     *
     * @param user user
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        if (userService.addUser(user)) {
            return user;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add failed");
        }
    }

    /**
     * 根据 id 更新用户
     *
     * @param id id
     * @param user user
     */
    @SaCheckLogin
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object updateUser(@RequestParam Long id, @RequestBody User user) {
        if (userService.updateUser(id, user)) {
            return user;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "update failed");
        }
    }

    /**
     * 根据用户名更新用户
     *
     * @param username username
     * @param user user
     */
    @SaCheckLogin
    @RequestMapping(value = "/name", method = RequestMethod.PUT)
    public Object updateUserByUsername(@RequestParam String username, @RequestBody User user) {
        if (userService.updateUser(username, user)) {
            return user;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "update failed");
        }
    }

    /**
     * 根据 id 更新用户头像
     *
     * @param id id
     * @param file file
     */
    @SaCheckLogin
    @RequestMapping(value = "/avatar", method = RequestMethod.PUT)
    public Object updateUserAvatar(@RequestParam Long id, @RequestParam(value = "file") MultipartFile file) {
        User user = userService.findUserById(id);
        String avatar = fileService.fileUpload(file);
        user.setAvatar(avatar);
        if (userService.updateUser(id, user)) {
            return user;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "update failed");
        }
    }

    /**
     * 删除用户
     *
     * @param id id
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("id") Long id) {
        if (userService.deleteUser(id)) {
            return id;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "delete failed");
        }
    }

}
