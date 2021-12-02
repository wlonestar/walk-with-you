package com.wjl.site.repository;

import com.wjl.site.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 19:18
 * @version: v1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

}
