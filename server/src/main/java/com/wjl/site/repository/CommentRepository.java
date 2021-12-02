package com.wjl.site.repository;

import com.wjl.site.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言实体仓库
 *
 * @author: wjl
 * @date: 2021/11/19 18:04
 * @version: v1.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long id);

    List<Comment> findCommentByAuthorId(Long authorId);

}
