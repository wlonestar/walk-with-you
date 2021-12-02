package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 角色权限关联实体
 *
 * @author: wjl
 * @date: 2021/11/18 21:19
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "role_permission")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rid")
    private Long rid;

    @Column(name = "pid")
    private Long pid;

}
