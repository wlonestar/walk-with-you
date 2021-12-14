package com.wjl.site.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.wjl.site.common.constants.Result;
import com.wjl.site.common.enums.HttpStatus;
import com.wjl.site.model.dto.CommentSubmitDto;
import com.wjl.site.model.dto.UserCommentDto;
import com.wjl.site.service.intf.CommentService;
import com.wjl.site.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 留言接口
 *
 * @author: wjl
 * @date: 2021/11/19 21:22
 * @version: v1.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 分页查询留言
     *
     * @param page page
     * @param size size
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<UserCommentDto> findAllCommentsByPage(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "5") int size) {
        return commentService.findAllCommentsByPage(PageRequest.of(page - 1, size));
    }

    /**
     * 获取所有留言
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserCommentDto> findAllComments() {
        return commentService.findAllComments();
    }

    /**
     * 根据用户 id 获取留言
     *
     * @param authorId authorId
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public List<UserCommentDto> findCommentsByAuthorId(@RequestParam Long authorId) {
        return commentService.findCommentsByAuthorId(authorId);
    }

    /**
     * 根据用户名获取留言
     *
     * @param username username
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public Object findCommentsByAuthorId(@RequestParam String username) {
        if (!Objects.isNull(userService.findUserByUsername(username))) {
            return commentService.findCommentsByUsername(username);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find user");
        }
    }

    /**
     * 根据 id 获取留言
     *
     * @param id id
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object findCommentById(@RequestParam Long id) {
        if (!Objects.isNull(commentService.findCommentById(id))) {
            return commentService.findCommentById(id);
        } else {
            return Result.fail(HttpStatus.NOT_FOUNT.getCode(), "can't find comment");
        }
    }

    /**
     * 添加留言
     *
     * @param commentSubmitDto comment
     */
//    @SaCheckLogin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addComment(@RequestBody CommentSubmitDto commentSubmitDto) {
        if (commentService.addComment(commentSubmitDto)) {
            return commentSubmitDto;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "add failed");
        }
    }

    /**
     * 删除留言
     *
     * @param id id
     */
    @SaCheckRole({"super_admin", "admin"})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteComment(@PathVariable("id") Long id) {
        if (commentService.deleteComment(id)) {
            return id;
        } else {
            return Result.fail(HttpStatus.FAILED.getCode(), "delete failed");
        }
    }

}
