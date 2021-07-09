package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BookMapper {
    //    找到该书的价钱
    @Select("select price from book where id =  #{bookid}")
    int getBookPrice(String bookid);
    //    找到该书的店铺
    @Select("select storeId from book where id =  #{bookid}")
    String getStoreOfBook(String bookid);
}
