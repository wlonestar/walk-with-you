package com.wjl.site.model.dto;

import lombok.Data;

import javax.persistence.Column;

/**
 * 用户角色传输类
 *
 * @author: wjl
 * @date: 2021/11/20 10:12
 * @version: v1.0
 */
@Data
public class UserRoleDto {

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String description;

    private String location;

    private String education;

    private String profile;

    private String role;

}
