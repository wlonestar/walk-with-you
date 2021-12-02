package com.wjl.site.repository;

import com.wjl.site.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 19:18
 * @version: v1.0
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    List<RolePermission> findAllByRid(Long rid);

}
