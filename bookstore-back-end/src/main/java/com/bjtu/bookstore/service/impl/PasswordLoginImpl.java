package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.UserMapper;
import com.bjtu.bookstore.service.UserLoginInterface;
import com.bjtu.bookstore.utils.encodeUtils.EncodeUtil;
import com.bjtu.bookstore.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.bookstore.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.bookstore.utils.token.JWTUtils;
import com.bjtu.bookstore.utils.token.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @program: questionPlatform_back_end
 * @description: username password login impl
 * @author: CodingLiOOT
 * @create: 2021-06-17 20:47
 * @version: 1.0
 **/
@Component("password")
public class PasswordLoginImpl implements UserLoginInterface {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EncodeUtil encodeUtil;
    @Autowired
    private JwtUserServiceImpl jwtUserService;
    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public HashMap<String, String> userLogin(User user) {
        User userBean = userMapper.selectUserByUserName(user.getUsername());
        if (userBean == null || !encodeUtil.verifyEncode(user.getPassword(), userBean.getMail(), userBean.getPassword())) {
            throw new DefinitionException(ErrorEnum.ERROR_NICKNAME_OR_PASSWORD);
        }
        JwtUser userDetails = (JwtUser) jwtUserService.loadUserByUsername(user.getUsername());
        return new HashMap<String, String>() {
            {
                put("token", jwtUtils.generateToken(userDetails));
                put("userID", String.valueOf(userDetails.getID()));
                put("state", String.valueOf(userDetails.getState()));
            }
        };
    }
}
