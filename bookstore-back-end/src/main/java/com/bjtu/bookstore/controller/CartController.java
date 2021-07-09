package com.bjtu.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Store;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.UserMapper;
import com.bjtu.bookstore.service.CartService;
import com.bjtu.bookstore.service.UserService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/cart")
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/cart/getAllCart")
    public HashMap<String, Object> getAllCart(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        Preconditions.checkState(
                (StringUtils.equals(user.getLoginType(), "mail")
                        && StringUtils.isNoneBlank(user.getMail(), user.getVerifyCode()))
                        || (StringUtils.equals(user.getLoginType(), "password")
                        && StringUtils.isNoneBlank(user.getUsername(), user.getPassword()))
        );

        return cartService.getAllCart(user);
    }

    @PostMapping(value = "/cart/settlement")
    public int settlement(@RequestBody JSONObject object) {
        Preconditions.checkNotNull(object);

        return cartService.calculate(object);
    }

    @PostMapping(value = "/addtocart")
    public int addtocart(@RequestBody Cart cart) {
        Preconditions.checkNotNull(cart);

        return cartService.addtocart(cart);
    }

    @PostMapping(value = "/deletefromcart")
    public int deletefromcart(@RequestBody JSONObject object) {
        Preconditions.checkNotNull(object);

        return cartService.deletefromcart(object);
    }

    @PostMapping(value = "/modifyNumFromCart")
    public int modifyNumFromCart(@RequestBody JSONObject object) {
        Preconditions.checkNotNull(object);

        return cartService.deletefromcart(object);
    }
}
