package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.service.MailService;
import com.bjtu.bookstore.service.UserService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: framework
 * @description: user controller
 * @author: CodingLiOOT
 * @create: 2021-03-18 20:44
 * @version: 1.0
 **/
@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/login")
    public Pair<String, String> login(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkState(
                !(StringUtils.equals(user.getLoginType(), "mail")
                        && StringUtils.isNoneBlank(user.getMail(), user.getVerifyCode())
                        || !(StringUtils.equals(user.getLoginType(), "password")
                        && StringUtils.isNoneBlank(user.getUsername(), user.getPassword()))));

        return Pair.of("token", userService.userLogin(user));
    }


    @PostMapping(value = "/sendVerifyCode")
    public void sendVerifyCode(@RequestBody User user) {
        Preconditions.checkNotNull(user.getMail());

        mailService.sendMail(user.getMail());
    }


    @PostMapping(value = "/register")
    public void register(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkState(StringUtils.isNoneBlank(user.getMail(), user.getVerifyCode()));

        userService.register(user);
    }

    @PostMapping(value = "/forgetPassword")
    public void forgetPassword(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkState(StringUtils.isNoneBlank(user.getMail(), user.getNewPassword(), user.getVerifyCode()));

        userService.forgetPassword(user);
    }

    @PostMapping(value = "/modifyPassword")
    public void modifyPassword(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkState(StringUtils.isNoneBlank(user.getUsername(), user.getPassword(), user.getNewPassword()));

        userService.modifyPassword(user);

    }
}
