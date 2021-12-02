package com.wjl.site.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * 用户留言传输类
 *
 * @author: wjl
 * @date: 2021/11/19 21:25
 * @version: v1.0
 */
@Data
public class UserCommentDto {

    private Long id;

    private Long authorId;

    private String content;

    private Date createTime;

    private String username;

    private String avatar;

}
