package com.wjl.site.repository;

import com.wjl.site.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 19:19
 * @version: v1.0
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAllByUid(Long uid);

    UserRole findByUid(Long uid);

}
