package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 人物实体
 *
 * @author: wjl
 * @date: 2021/11/21 14:19
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "people")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @Lob
    @Column(name = "introduction")
    private String introduction;

    @Lob
    @Column(name = "mention")
    private String mention;

}
