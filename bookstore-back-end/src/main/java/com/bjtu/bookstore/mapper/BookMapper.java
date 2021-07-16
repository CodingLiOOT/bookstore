package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    //    找到所有正常的书或者违规的书
    @Select("select * from book where state=#{state} LIMIT #{n}, #{m}")
    ArrayList<Book> getRightBooks(int state, int n, int m);

    //    找到所有正常的书或者违规的书的数目
    @Select("select count(*) from book where state=#{state}")
    int getRightNum(int state);

    //    管理员修改书的状态
    @Update("update book set state=#{state} where id=#{id}")
    int changeBookState(int state, Long id);

    //    找到该书的价钱
    @Select("select price from book where state=1 and id = #{bookid}")
    int getBookPrice(Long bookid);

    //    找到该书的名字
    @Select("select name from book where id = #{bookid}")
    String getBookNameById(Long bookid);

    //    找到该书的详情
    @Select("select * from book where id = #{bookid}")
    ArrayList<Book> getDetail(Long bookid);

    //    找到该书的类别
    @Select("select categoryId from book where id = #{bookid}")
    String getCategory(Long bookid);

    //    添加点击次数
    @Insert("insert into userbookclick values(#{categoryId}, #{userId}, 1) on duplicate key update clickNum=clickNum+1")
    void addUserBookClick(Long userId, String categoryId);

    //    找到用户点击最多的类别
    @Select("select categoryId from userbookclick where userId=#{userId} and clickNum = (select max(clickNum) from userbookclick where userId=#{userId})")
    List<String> getMaxCategory(Long userId);

    //    找到用户点击最多的类别，该类别销量前五
    @Select("select * from book where categoryId=#{categoryId} order by dealNum desc limit 5")
    ArrayList<Book> getTopFive(String categoryId);

    //    找到该书的店铺
    @Select("select storeId from book where state=1 and id = #{bookid}")
    String getStoreOfBook(String bookid);

    //    获取最新的五本书
    @Select("SELECT * FROM book where state=1 ORDER BY createdTime DESC LIMIT 5")
    List<Book> getNewBooks();

    //    获取销量最高的五本书
    @Select("SELECT * FROM book where state=1 ORDER BY dealNum DESC LIMIT 5")
    List<Book> getTopBooks();

    //    返回某类别的某一页的书，返回第n到第m-1行的记录
    @Select("SELECT * FROM book where state=1 and categoryId=#{categoryId} LIMIT #{n}, #{m}")
    ArrayList<Book> getSomePageBookByCategory(int n, int m, String categoryId);

    //    返回无类别的某一页的书，返回第n到第m-1行的记录
    @Select("SELECT * FROM book where state=1 LIMIT #{n}, #{m}")
    ArrayList<Book> getSomePageBook(int n, int m);

    //    返回某类别的书的数量
    @Select("SELECT count(*) FROM book where state=1 and categoryId=#{categoryId}")
    int getAllNumByCategory(String categoryId);

    //    返回无类别的书的数量
    @Select("SELECT count(*) FROM book where state=1")
    int getAllNum();

    //    返回根据搜索内容的某一页的书，返回第n到第m-1行的记录
    @Select("SELECT * FROM book where state=1 and name like #{content} LIMIT #{n}, #{m}")
    ArrayList<Book> search(int n, int m, String content);

    //    返回根据搜索内容书的数量
    @Select("SELECT count(*) FROM book where state=1 and name like #{content}")
    int getAllNumByContent(String content);

    @Update("update book set state=0 where id=#{id}")
    int updateState(Long id);

    @Update("update book set dealNum=dealNum+1 where id=#{id}")
    int updateDealNum(Long id);
}
