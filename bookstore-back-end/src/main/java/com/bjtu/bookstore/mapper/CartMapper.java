package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Store;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface CartMapper {
    //    找到该用户的购物车中的图书对应的店铺
//    @Select("select * from store where id = (select distinct storeid from cart where userid = #{userid})")
//    ArrayList<Store> getAllCartStores(String userid);
    //    找到该用户的购物车中的图书对应的店铺中有哪些书被放入了这个用户的购物车
    @Select("select * from book where id in (select bookid from cart where userid = #{userid})")
    ArrayList<Book> getAllCartBooks(String userid);
    @Select("select * from cart where userid = #{userid}")
    ArrayList<Cart> getAllCartBooks2(String userid);
    //    加入购物车
    @Insert("insert into cart values(#{userid},#{bookid},#{num})")
    int addtocart(String userid, String bookid, int num);
    //    删除购物车中的书
    @Delete("delete from cart where userid=#{userid} and bookid=#{bookid}")
    int deletefromcart(String userid, String bookid);
    //    修改购物车中的书的数目
    @Update("update cart set num=#{num} where userid=#{userid} and bookid=#{bookid}")
    int modifyNumFromCart(String userid, String bookid, int num);
}