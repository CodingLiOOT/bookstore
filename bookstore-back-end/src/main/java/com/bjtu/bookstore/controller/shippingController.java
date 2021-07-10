package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Shipping;
import com.bjtu.bookstore.service.ShippingService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/user")
public class shippingController {


    @Autowired
    private ShippingService shippingService;


    //通过id获得地址信息
    @PostMapping(value = "/getAddress")
    public List<Shipping> getAddress(@RequestBody Shipping userId) {
        Preconditions.checkNotNull(userId);

        return shippingService.getAddress(userId.getUserId()) ;
    }

    //为用户添加一个地址
    @PostMapping(value = "/addAddress")
    public void addAddress(@RequestBody Shipping shipping) {
        Preconditions.checkNotNull(shipping);
        Preconditions.checkNotNull(shipping.getUserId());
        shippingService.addAddress(shipping);

    }

    //修改用户的其中一个地址
    @PostMapping(value = "/modifyAddress")
    public void modifyAddress(@RequestBody Shipping shipping) {
        Preconditions.checkNotNull(shipping);
        Preconditions.checkNotNull(shipping.getUserId());
        shippingService.modifyAddress(shipping);

    }


    //删除用户的其中一个地址
    @PostMapping(value = "/deleteAddress")
    public void deleteAddress(@RequestBody Shipping shipping) {
        Preconditions.checkNotNull(shipping);


        shippingService.deleteAddress(shipping);

    }

}
