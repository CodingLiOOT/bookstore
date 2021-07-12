package com.bjtu.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.bookstore.entity.Order;
import com.bjtu.bookstore.service.OrderService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/order")
public class OrderController {
    @Autowired
   private OrderService orderService;


  @PostMapping("/getOrderList")
  public  List<HashMap<String,Object>> getOrderList(@RequestBody  Order order){
      List<HashMap<String, Object>> orderList = orderService.getOrderList(order);
      return orderList;
  }

    @PostMapping("/changestate")
    public   void changestate(@RequestBody Order order){
      orderService.changestate(order);

    }


  @PostMapping("/confirmOrder")
  public  HashMap<String,Object> confirmOrder(@RequestBody Order data){


      return orderService.confirmOrder(data);



  }


}
