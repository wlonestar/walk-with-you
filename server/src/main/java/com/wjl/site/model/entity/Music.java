package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 音乐实体
 *
 * @author: wjl
 * @date: 2021/11/18 21:24
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "music")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "link")
    private String link;

    @Lob
    @Column(name = "background")
    private String background;

    @Lob
    @Column(name = "appreciation")
    private String appreciation;

}
