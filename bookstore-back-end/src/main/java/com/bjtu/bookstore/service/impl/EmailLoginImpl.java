package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.UserMapper;
import com.bjtu.bookstore.service.UserLoginInterface;
import com.bjtu.bookstore.utils.token.JWTUtils;
import com.bjtu.bookstore.utils.token.JwtUser;
import com.bjtu.bookstore.utils.verifyCodeUtils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @program: questionPlatform_back_end
 * @description: email login impl
 * @author: CodingLiOOT
 * @create: 2021-06-17 20:46
 * @version: 1.0
 **/
@Component("mail")
public class EmailLoginImpl implements UserLoginInterface {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerifyCodeUtils verifyCodeUtils;
    @Autowired
    private JwtUserServiceImpl jwtUserService;
    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public HashMap<String, String> userLogin(User user) {
        user.setUsername(userMapper.selectUsernameByMail(user.getMail()));
        verifyCodeUtils.verifyCode(user.getMail(), user.getVerifyCode());
        JwtUser userDetails = (JwtUser) jwtUserService.loadUserByUsername(user.getUsername());
        return new HashMap<String, String>() {
            {
                put("token", jwtUtils.generateToken(userDetails));
                put("userID", userDetails.getID());
            }
        };
    }
}
