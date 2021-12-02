package com.wjl.site.service.impl;

import com.wjl.site.model.entity.Permission;
import com.wjl.site.repository.PermissionRepository;
import com.wjl.site.service.intf.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 权限服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 19:23
 * @version: v1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission fnidPermissionById(Long pid) {
        return permissionRepository.findPermissionById(pid);
    }

    @Override
    public String addPermission(Permission permission) {
        if (!Objects.isNull(permission)) {
            permissionRepository.save(permission);
            return permission.getPermission();
        }
        return null;
    }

}
