package com.bjtu.bookstore.mapper;


import com.bjtu.bookstore.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {


    @Select("select* from orders where userId=#{id}")
    List<Order> getOrderList(Order order);

    @Update("update orders set state=#{state} where id=#{id}")
    void changestate(Order order);

    @Insert("insert into orders values(#{id},#{shippingId},#{userId},#{createdTime},#{completedTime},#{totalPrice},#{payType},#{state})")
    void confirmOrder(Order order);







}
