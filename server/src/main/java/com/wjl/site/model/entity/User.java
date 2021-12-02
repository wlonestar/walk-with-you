package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wjl.site.common.constants.GlobalConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 用户实体
 *
 * @author: wjl
 * @date: 2021/11/18 21:09
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar = GlobalConstants.AVATAR;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "education")
    private String education;

    @Column(name = "profile")
    private String profile;

}
