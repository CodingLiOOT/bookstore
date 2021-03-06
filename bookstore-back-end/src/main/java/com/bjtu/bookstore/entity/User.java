package com.bjtu.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: framework
 * @description: user entity
 * @author: CodingLiOOT
 * @create: 2021-03-18 19:36
 * @version: 1.0
 **/
@Data

public class User implements Serializable {

    private String loginType;

    private String verifyCode;

    private Long id;

    private String avatar;

    private String username;

    private String password;

    private String newPassword;

    private String type;

    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdDate;

    private String phone;

    private String mail;

    // 普通用户能用是1，不能用是0，管理员是2
    private Integer state;

    private List<Role> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp birthday;

    private Integer gender;

    private String storeId;

    private int startNum;
}
