package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 用户角色关联实体
 *
 * @author: wjl
 * @date: 2021/11/18 21:17
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "user_role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "rid")
    private Long rid;

}
