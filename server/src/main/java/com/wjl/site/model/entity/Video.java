package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author: wjl
 * @date: 2021/11/22 16:28
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "video")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "poster")
    private String poster;

    @Column(name = "link")
    private String link;

    @Lob
    @Column(name = "introduction")
    private String introduction;

}
