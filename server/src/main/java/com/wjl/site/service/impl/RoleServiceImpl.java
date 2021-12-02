package com.wjl.site.service.impl;

import com.wjl.site.model.dto.RolePermissionDto;
import com.wjl.site.model.entity.Permission;
import com.wjl.site.model.entity.Role;
import com.wjl.site.model.entity.RolePermission;
import com.wjl.site.repository.PermissionRepository;
import com.wjl.site.repository.RolePermissionRepository;
import com.wjl.site.repository.RoleRepository;
import com.wjl.site.service.intf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 角色服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 19:24
 * @version: v1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long rid) {
        return roleRepository.findRoleById(rid);
    }

    @Override
    public Role findRoleByRoleName(String role) {
        return roleRepository.findRoleByRole(role);
    }

    @Override
    public String addRole(Role role) {
        if (!Objects.isNull(role)) {
            roleRepository.save(role);
            return role.getRole();
        }
        return null;
    }

    @Override
    public boolean addRolePermission(RolePermissionDto rolePermissionDto) {
        if (!Objects.isNull(rolePermissionDto)) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(roleRepository.findRoleByRole(rolePermissionDto.getRole()).getId());
            rolePermission.setPid(permissionRepository.findPermissionByPermission(rolePermissionDto.getPermission()).getId());
            rolePermissionRepository.save(rolePermission);
            return true;
        }
        return false;
    }

}
