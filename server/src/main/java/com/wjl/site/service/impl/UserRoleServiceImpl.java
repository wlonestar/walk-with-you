package com.wjl.site.service.impl;

import com.wjl.site.model.entity.User;
import com.wjl.site.model.entity.UserRole;
import com.wjl.site.repository.UserRoleRepository;
import com.wjl.site.service.intf.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 19:25
 * @version: v1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> findRolesByUser(User user) {
        return userRoleRepository.findAllByUid(user.getId());
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

}
