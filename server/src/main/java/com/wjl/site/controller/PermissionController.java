package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.entity.Permission;
import com.wjl.site.service.intf.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 权限接口
 *
 * @author: wjl
 * @date: 2021/11/20 9:39
 * @version: v1.0
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取所有权限
     */
    @SaCheckRole("super_admin")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Permission> findAllPermission() {
        return permissionService.findAllPermission();
    }

    /**
     * 添加权限
     *
     * @param permission permission
     */
    @SaCheckRole("super_admin")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addPermission(@RequestBody Permission permission) {
        if (!Objects.isNull(permissionService.addPermission(permission))) {
            return permission;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add failed");
        }
    }

}
