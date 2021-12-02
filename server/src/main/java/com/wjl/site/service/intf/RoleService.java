package com.wjl.site.service.intf;

import com.wjl.site.model.dto.RolePermissionDto;
import com.wjl.site.model.entity.Role;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:24
 * @version: v1.0
 */
public interface RoleService {

    List<Role> findAllRoles();

    Role findRoleById(Long rid);

    Role findRoleByRoleName(String role);

    String addRole(Role role);

    boolean addRolePermission(RolePermissionDto rolePermissionDto);

}
