package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Shipping;


import java.util.List;

public interface ShippingService {

    //通过用户id获取地址信息
    List<Shipping> getAddress(String uid);



    //添加地址信息
    void addAddress(Shipping user);


    //修改收货地址信息
    void modifyAddress(Shipping shipping);


    //删除收货地址
    void deleteAddress(Shipping shipping);


}
