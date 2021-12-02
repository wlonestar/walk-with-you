package com.wjl.site.repository;

import com.wjl.site.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 权限实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 19:17
 * @version: v1.0
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findPermissionById(Long id);

    Permission findPermissionByPermission(String permission);

}
