package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Store;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface CartMapper {
    //    找到该用户的购物车中的图书对应的店铺
    @Select("select * from store where id = (select storeid from cart where userid = #{userid})")
    ArrayList<Store> getAllCartStores(String userid);
    //    找到该用户的购物车中的图书对应的店铺中有哪些书被放入了这个用户的购物车
    @Select("select * from book where id = (select bookid from cart where userid = #{userid} and storeid = #{storeid})")
    ArrayList<Book> getAllCartBooksOfStores(String userid, String storeid);
    //    加入购物车
    @Select("insert into cart values(#{userid},#{bookid},#{storeid},#{num})")
    int addtocart(String userid, String bookid, String storeid, int num);
    //    删除购物车中的书
    @Select("delete from cart where userid=#{userid} and bookid=#{bookid}")
    int deletefromcart(String userid, String bookid);
    //    修改购物车中的书的数目
    @Select("update cart set num=#{num} where userid=#{userid} and bookid=#{bookid}")
    int modifyNumFromCart(String userid, String bookid, int num);
}