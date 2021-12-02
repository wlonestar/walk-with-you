package com.wjl.site.model.dto;

import lombok.Data;

/**
 * 用户登录传输类
 *
 * @author: wjl
 * @date: 2021/11/20 10:57
 * @version: v1.0
 */
@Data
public class UserLoginDto {

    private String username;

    private String password;

}
