package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.dto.RolePermissionDto;
import com.wjl.site.model.entity.Role;
import com.wjl.site.service.intf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 角色接口
 *
 * @author: wjl
 * @date: 2021/11/20 9:21
 * @version: v1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     */
    @SaCheckRole("super_admin")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> findAllRoles() {
        return roleService.findAllRoles();
    }

    /**
     * 添加角色
     *
     * @param role role
     */
    @SaCheckRole("super_admin")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addRole(@RequestBody Role role) {
        if (!Objects.isNull(roleService.addRole(role))) {
            return role;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add failed");
        }
    }

    /**
     * 添加角色权限
     *
     * @param rolePermissionDto rolePermissionDto
     */
    @SaCheckRole("super_admin")
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public Object addRolePermission(@RequestBody RolePermissionDto rolePermissionDto) {
        if (roleService.addRolePermission(rolePermissionDto)) {
            return rolePermissionDto;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add failed");
        }
    }

}
