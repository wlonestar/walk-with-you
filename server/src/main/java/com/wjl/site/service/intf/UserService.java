package com.wjl.site.service.intf;

import com.wjl.site.model.dto.UserRoleDto;
import com.wjl.site.model.entity.User;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 19:25
 * @version: v1.0
 */
public interface UserService {

    List<User> findAllUsers();

    List<UserRoleDto> findAllUserRole();

    List<String> findAllUsername();

    User findUserById(Long id);

    User findByUsername(String username);

    UserRoleDto findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    boolean addUser(User user);

    boolean updateUser(Long id, User user);

    boolean updateUserAvatar(Long id, User user);

    boolean updateUser(String username, User user);

    boolean deleteUser(Long id);

}
