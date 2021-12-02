package com.wjl.site.service.impl;

import com.wjl.site.model.entity.Role;
import com.wjl.site.model.entity.RolePermission;
import com.wjl.site.repository.RolePermissionRepository;
import com.wjl.site.service.intf.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 19:24
 * @version: v1.0
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public List<RolePermission> findAllByRole(Role role) {
        return rolePermissionRepository.findAllByRid(role.getId());
    }

}
