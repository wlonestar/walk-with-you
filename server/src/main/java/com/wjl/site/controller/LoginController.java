package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaBase64Util;
import cn.dev33.satoken.stp.StpUtil;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.dto.UserLoginDto;
import com.wjl.site.model.dto.UserRegisterDto;
import com.wjl.site.model.entity.Role;
import com.wjl.site.model.entity.User;
import com.wjl.site.model.entity.UserRole;
import com.wjl.site.service.intf.RoleService;
import com.wjl.site.service.intf.UserRoleService;
import com.wjl.site.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 登录接口
 *
 * @author: wjl
 * @date: 2021/11/20 10:15
 * @version: v1.0
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 登录
     *
     * @param userLoginDto user
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        if (userService.findUserByUsernameAndPassword(username, SaBase64Util.encode(password)) != null) {
            StpUtil.login(username);
            User user = userService.findByUsername(username);
            user.setToken(StpUtil.getTokenValue());
            userService.updateUser(username, user);
            return StpUtil.getTokenInfo();
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "登录失败，用户名或密码错误");
        }
    }

    /**
     * 验证是否登录
     *
     * @param username username
     * @param tokenValue token
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Object checkLogin(@RequestParam String username, @RequestParam String tokenValue) {
        if (!Objects.isNull(username) && !Objects.isNull(tokenValue)) {
            if (StpUtil.isLogin()) {
                String loginId = StpUtil.getLoginIdAsString();
                User user = userService.findByUsername(loginId);
                if (user.getUsername().equals(username) && user.getToken().equals(tokenValue)) {
                    return Result.success("已登录");
                } else {
                    return Result.fail(HttpStatus.FAILED.getCode(), "token 已过期，请重新登录");
                }
            } else {
                return Result.fail(HttpStatus.FAILED.getCode(), "尚未登录");
            }
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "发生异常");
        }
    }

    /**
     * 注销
     *
     * @param tokenValue token
     */
    @SaCheckLogin
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result<User> logout(@RequestParam String tokenValue) {
        if (!Objects.isNull(tokenValue)) {
            StpUtil.checkLogin();
            StpUtil.logoutByTokenValue(tokenValue);
            return Result.success("logout success");
        }
        return Result.fail(HttpStatus.FAILED.getCode(), "logout failed");
    }

    /**
     * 注册
     *
     * @param userRegisterDto user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String passwordRepeat = userRegisterDto.getPasswordRepeat();
        List<String> usernames = userService.findAllUsername();
        if (!usernames.contains(username)) {
            if (passwordRepeat.equals(password)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(SaBase64Util.encode(password));
                userService.addUser(user);
                User user1 = userService.findByUsername(username);
                Long uid = user1.getId();
                Role role = roleService.findRoleByRoleName("user");
                Long rid = role.getId();
                UserRole userRole = new UserRole();
                userRole.setUid(uid);
                userRole.setRid(rid);
                userRoleService.addUserRole(userRole);
                return Result.success("register success");
            } else {
                return Result.fail(HttpStatus.FAILED.getCode(), "The two passwords entered are inconsistent");
            }
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "username exists");
        }
    }

}
