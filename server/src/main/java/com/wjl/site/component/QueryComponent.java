package com.wjl.site.component;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * querydsl 查询组件
 *
 * @author: wjl
 * @date: 2021/11/19 21:28
 * @version: v1.0
 */
@Component
public class QueryComponent {

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
