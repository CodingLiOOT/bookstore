package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Order;
import com.bjtu.bookstore.entity.Order_book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Order_bookMapper {

    @Select("select * from order_book where orderId=#{id}")
    List<Order_book> getByOrderId(String id);


    @Insert("insert into order_book values(#{orderId},#{bookId},#{amount},#{storeId},#{state})")
    void confirmOrderBook(Order_book order_book);
}
