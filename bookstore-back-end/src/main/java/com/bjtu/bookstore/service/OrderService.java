package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Order;

import java.util.HashMap;
import java.util.List;


public interface OrderService {


    List<HashMap<String,Object>> getOrderList(Order order);


    void changestate(Order order);


    void confirmOrder(Order order);
}
