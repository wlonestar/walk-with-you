package com.wjl.site.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 事件实体
 *
 * @author: wjl
 * @date: 2021/11/18 21:21
 * @version: v1.0
 */
@Getter
@Setter
@Entity
@Table(name = "event")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;

    @Lob
    @Column(name = "introduction")
    private String introduction;

}
