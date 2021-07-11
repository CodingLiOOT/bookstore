package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Shipping;
import com.bjtu.bookstore.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
@Repository
public interface ShippingMapper {
    //通过用户id获取地址信息

    @Select("<script>"
            +"select * from shipping where userId=#{uid}"
            +"</script>"
    )
    List<Shipping> getAddress(String uid);



    //添加地址信息
    @Insert("<script>"
            +"insert into shipping (userId,consigneeName,address ,phone, id) values (#{userId},#{consigneeName},#{address},#{phone},#{id})"
            +"</script>"
    )
    void addAddress(Shipping user);


    //修改收货地址信息
    @Update("<script>"
            +"update shipping  "
            +"<set>"
            +" <if test=\"userId != null\">userId=#{userId},</if>"
            +" <if test=\"consigneeName != null\">consigneeName=#{consigneeName},</if>"
            +" <if test=\"phone != null\">phone=#{phone},</if>"
            +" <if test=\"address != null\">address=#{address}</if>"
            +"</set>"
            +"where userId=#{userId} and id=#{id}"
            +"</script>"
    )
    void modifyAddress(Shipping shipping);


    //删除收货地址
    @Delete("<script>"
                      +"delete  from  shipping" +
                   " where id= #{id}"

                   + "</script>"
    )
    void deleteAddress(Shipping shipping);




}
