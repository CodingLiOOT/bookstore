package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Mapper
@Repository
public interface CommentMapper {
    //    获取某一本书的所有评论
    @Select("SELECT * FROM comment where bookId=#{bookId}")
    ArrayList<Comment> getCommentByBook(Long bookId);

    //    获取某一个订单的所有评论
    @Select("SELECT * FROM comment where orderId=#{orderId}")
    ArrayList<Comment> getCommentByOrder(String orderId);

    //    添加评论
    @Insert("insert into comment values(#{commentId},#{userId},#{bookId},#{orderId},#{comment},Now(),#{rate})")
    int sendComment(String commentId, Long userId, Long bookId, String orderId, String comment, Date date, int rate);
}
