package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    //    找到该书的价钱
    @Select("select price from book where id =  #{bookid}")
    int getBookPrice(String bookid);

    //    找到该书的详情
    @Select("select * from book where id =  #{bookid}")
    ArrayList<Book> getDetail(String bookid);

    //    找到该书的店铺
    @Select("select storeId from book where id =  #{bookid}")
    String getStoreOfBook(String bookid);

    //    获取最新的五本书
    @Select("SELECT * FROM book ORDER BY createdTime DESC LIMIT 5")
    List<Book> getNewBooks();

    @Select("SELECT * FROM book ORDER BY dealNum DESC LIMIT 5")
    List<Book> getTopBooks();

    //    返回某类别的某一页的书，返回第n到第m-1行的记录
    @Select("SELECT * FROM book where categoryId=#{categoryId} LIMIT #{n}, #{m}")
    ArrayList<Book> getSomePageBookByCategory(int n, int m, String categoryId);

    //    返回某类别的某一页的书，返回第n到第m-1行的记录
    @Select("SELECT * FROM book LIMIT #{n}, #{m}")
    ArrayList<Book> getSomePageBook(int n, int m);

    //    返回书的数量
    @Select("SELECT count(*) FROM book")
    int getAllNum();
}
