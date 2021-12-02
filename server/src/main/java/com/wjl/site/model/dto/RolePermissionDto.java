package com.wjl.site.model.dto;

import lombok.Data;

/**
 * 角色权限传输类
 *
 * @author: wjl
 * @date: 2021/11/20 9:52
 * @version: v1.0
 */
@Data
public class RolePermissionDto {

    private Long id;

    private String role;

    private String permission;

}
