package com.wjl.site.repository;

import com.wjl.site.model.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 人物实体仓库
 *
 * @author: wjl
 * @date: 2021/11/21 14:22
 * @version: v1.0
 */
@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    People findPeopleById(Long id);

}
