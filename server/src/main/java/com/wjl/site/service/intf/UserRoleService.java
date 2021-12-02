package com.wjl.site.service.intf;

import com.wjl.site.model.entity.User;
import com.wjl.site.model.entity.UserRole;

import java.util.List;

/**
 * 用户角色服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:25
 * @version: v1.0
 */
public interface UserRoleService {

    List<UserRole> findAll();

    List<UserRole> findRolesByUser(User user);

    UserRole addUserRole(UserRole userRole);

}
