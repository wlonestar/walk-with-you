package com.wjl.site.repository;

import com.wjl.site.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 19:17
 * @version: v1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleById(Long id);

    Role findRoleByRole(String role);

}
