package com.wjl.site.service.intf;

import com.wjl.site.model.entity.Role;
import com.wjl.site.model.entity.RolePermission;

import java.util.List;

/**
 * 角色权限服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:24
 * @version: v1.0
 */
public interface RolePermissionService {

    List<RolePermission> findAllByRole(Role role);

}
