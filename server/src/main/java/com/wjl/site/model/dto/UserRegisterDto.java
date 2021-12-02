package com.wjl.site.model.dto;

import lombok.Data;

/**
 * 用户注册传输类
 *
 * @author: wjl
 * @date: 2021/11/20 10:49
 * @version: v1.0
 */
@Data
public class UserRegisterDto {

    private String username;

    private String password;

    private String passwordRepeat;

}
