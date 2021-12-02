package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 权限实体
 *
 * @author: wjl
 * @date: 2021/11/18 21:16
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "permission")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission")
    private String permission;

}
