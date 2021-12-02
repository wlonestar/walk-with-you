package com.wjl.site.service.intf;

import com.wjl.site.model.entity.Permission;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:23
 * @version: v1.0
 */
public interface PermissionService {

    List<Permission> findAllPermission();

    Permission fnidPermissionById(Long pid);

    String addPermission(Permission permission);

}
