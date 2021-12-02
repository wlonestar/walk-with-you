package com.wjl.site.service.impl;

import com.wjl.site.component.QueryComponent;
import com.wjl.site.model.dto.UserRoleDto;
import com.wjl.site.model.entity.*;
import com.wjl.site.repository.RoleRepository;
import com.wjl.site.repository.UserRepository;
import com.wjl.site.repository.UserRoleRepository;
import com.wjl.site.service.intf.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 19:25
 * @version: v1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserRoleDto> findAllUserRole() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            UserRoleDto userRoleDto = new UserRoleDto();
            User user = userRepository.findUserById(userRole.getUid());
            Role role = roleRepository.findRoleById(userRole.getRid());
            BeanUtils.copyProperties(user, userRoleDto, "password", "token");
            userRoleDto.setRole(role.getRole());
            userRoleDtos.add(userRoleDto);
        }
        return userRoleDtos;
    }

    @Override
    public List<String> findAllUsername() {
        List<User> users = findAllUsers();
        List<String> usernames = new ArrayList<>();
        for (User user : users) {
            String username = user.getUsername();
            usernames.add(username);
        }
        return usernames;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserRoleDto findUserByUsername(String username) {
        UserRoleDto userRoleDto = new UserRoleDto();
        User user = userRepository.findUserByUsername(username);
        if (!Objects.isNull(user)) {
            UserRole userRole = userRoleRepository.findByUid(user.getId());
            if (!Objects.isNull(userRole)) {
                Role role = roleRepository.findRoleById(userRole.getRid());
                BeanUtils.copyProperties(user, userRoleDto, "password", "token");
                userRoleDto.setRole(role.getRole());
            }
        }
        return userRoleDto;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean addUser(User user) {
        if (!Objects.isNull(user)) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(Long id, User user) {
        if (!Objects.isNull(user)) {
            User user1 = findUserById(id);
            if (!Objects.isNull(user1)) {
                BeanUtils.copyProperties(user, user1, "id");
                userRepository.save(user1);
                userRepository.flush();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updateUserAvatar(Long id, User user) {
        return false;
    }

    @Override
    public boolean updateUser(String username, User user) {
        if (!Objects.isNull(user)) {
            User user1 = findByUsername(username);
            if (!Objects.isNull(user1)) {
                BeanUtils.copyProperties(user, user1, "id", "token", "password");
                userRepository.save(user1);
                userRepository.flush();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (!Objects.isNull(findUserById(id))) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
