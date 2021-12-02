package com.wjl.site.service.intf;

import com.wjl.site.model.dto.CommentSubmitDto;
import com.wjl.site.model.dto.UserCommentDto;
import com.wjl.site.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 留言服务接口
 *
 * @author: wjl
 * @date: 2021/11/19 18:11
 * @version: v1.0
 */
public interface CommentService {

    Page<UserCommentDto> findAllCommentsByPage(Pageable pageable);

    List<UserCommentDto> findAllComments();

    List<UserCommentDto> findCommentsByAuthorId(Long authorId);

    List<UserCommentDto> findCommentsByUsername(String username);

    UserCommentDto findCommentById(Long id);

    Comment findById(Long id);

    boolean addComment(CommentSubmitDto commentSubmitDto);

    boolean deleteComment(Long id);

}
