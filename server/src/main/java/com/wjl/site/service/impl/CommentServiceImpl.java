package com.wjl.site.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.wjl.site.component.QueryComponent;
import com.wjl.site.model.dto.CommentSubmitDto;
import com.wjl.site.model.dto.UserCommentDto;
import com.wjl.site.model.entity.Comment;
import com.wjl.site.model.entity.QComment;
import com.wjl.site.model.entity.QUser;
import com.wjl.site.repository.CommentRepository;
import com.wjl.site.repository.UserRepository;
import com.wjl.site.service.intf.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 留言服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 18:14
 * @version: v1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private QueryComponent queryComponent;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    QUser qUser = QUser.user;

    QComment qComment = QComment.comment;

    @Override
    public Page<UserCommentDto> findAllCommentsByPage(Pageable pageable) {
        QueryResults<Tuple> queryResults = queryComponent.queryFactory()
                .select(
                        qComment.id,
                        qComment.authorId,
                        qComment.content,
                        qComment.createTime,
                        qUser.username,
                        qUser.avatar
                )
                .from(qComment, qUser)
                .where(qComment.authorId.eq(qUser.id))
                .orderBy(qComment.createTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Tuple> tuples = queryResults.getResults();
        List<UserCommentDto> userCommentDtos = new ArrayList<>();
        for (Tuple tuple : tuples) {
            UserCommentDto userCommentDto = new UserCommentDto();
            userCommentDto.setId(tuple.get(qComment.id));
            userCommentDto.setAuthorId(tuple.get(qComment.authorId));
            userCommentDto.setContent(tuple.get(qComment.content));
            userCommentDto.setCreateTime(tuple.get(qComment.createTime));
            userCommentDto.setUsername(tuple.get(qUser.username));
            userCommentDto.setAvatar(tuple.get(qUser.avatar));
            userCommentDtos.add(userCommentDto);
        }
        return new PageImpl<>(userCommentDtos, pageable, queryResults.getTotal());
    }

    @Override
    public List<UserCommentDto> findAllComments() {
        return queryComponent.queryFactory()
                .select(
                        Projections.bean(
                                UserCommentDto.class,
                                qComment.id,
                                qComment.authorId,
                                qComment.content,
                                qComment.createTime,
                                qUser.username.as("username"),
                                qUser.avatar.as("avatar")
                        )
                )
                .from(qComment, qUser)
                .where(qComment.authorId.eq(qUser.id))
                .orderBy(qComment.createTime.desc())
                .fetch();
    }

    @Override
    public List<UserCommentDto> findCommentsByAuthorId(Long authorId) {
        return queryComponent.queryFactory()
                .select(
                        Projections.bean(
                                UserCommentDto.class,
                                qComment.id,
                                qComment.authorId,
                                qComment.content,
                                qComment.createTime,
                                qUser.username.as("username"),
                                qUser.avatar.as("avatar")
                        )
                )
                .from(qComment, qUser)
                .where(qComment.authorId.eq(authorId)
                        .and(qComment.authorId.eq(qUser.id)))
                .orderBy(qComment.createTime.desc())
                .fetch();
    }

    @Override
    public List<UserCommentDto> findCommentsByUsername(String username) {
        return findCommentsByAuthorId(userRepository.findUserByUsername(username).getId());
    }

    @Override
    public UserCommentDto findCommentById(Long id) {
        return queryComponent.queryFactory()
                .select(
                        Projections.bean(
                                UserCommentDto.class,
                                qComment.id,
                                qComment.authorId,
                                qComment.content,
                                qComment.createTime,
                                qUser.username.as("username"),
                                qUser.avatar.as("avatar")
                        )
                )
                .from(qComment, qUser)
                .where(qComment.id.eq(id).and(qComment.authorId.eq(qUser.id)))
                .orderBy(qComment.createTime.desc())
                .fetchOne();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findCommentById(id);
    }

    @Override
    public boolean addComment(CommentSubmitDto commentSubmitDto) {
        if (!Objects.isNull(commentSubmitDto)) {
            Comment comment = new Comment();
            comment.setContent(commentSubmitDto.getContent());
            if (!Objects.isNull(userRepository.findUserByUsername(commentSubmitDto.getUsername()))) {
                comment.setAuthorId(userRepository.findUserByUsername(commentSubmitDto.getUsername()).getId());
            } else {
                return false;
            }
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteComment(Long id) {
        if (!Objects.isNull(findById(id))) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
