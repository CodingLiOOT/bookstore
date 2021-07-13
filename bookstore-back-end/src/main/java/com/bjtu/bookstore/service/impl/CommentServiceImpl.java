package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Comment;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.mapper.CartMapper;
import com.bjtu.bookstore.mapper.CommentMapper;
import com.bjtu.bookstore.mapper.UserMapper;
import com.bjtu.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public HashMap<String, Object> getCommentByBook(Comment comment) {
        ArrayList<Comment> comments=commentMapper.getCommentByBook(comment.getBookId());

        for(int i=0;i<comments.size();i++) {
            comments.get(i).setUserName(userMapper.getInformation(comments.get(i).getUserId()).getUsername());
            comments.get(i).setBookName(bookMapper.getBookNameById(comments.get(i).getBookId()));
        }
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("commentList",comments);
        return datas;
    }

    @Override
    public HashMap<String, Object> getCommentByOrder(Comment comment) {
        ArrayList<Comment> comments=commentMapper.getCommentByOrder(comment.getOrderId());
        for(int i=0;i<comments.size();i++) {
            comments.get(i).setUserName(userMapper.getInformation(comments.get(i).getUserId()).getUsername());
            comments.get(i).setBookName(bookMapper.getBookNameById(comments.get(i).getBookId()));
        }
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("commentList",comments);
        return datas;
    }

    @Override
    public void sendComment(Comment comment) {
        ArrayList<Book> books=comment.getBookList();

        for(int i=0;i<books.size();i++) {
            Book b1=books.get(i);
            b1.setCommentId(UUID.randomUUID().toString());
            commentMapper.sendComment(b1.getCommentId(),comment.getUserId(),b1.getId(),comment.getOrderId(),
                    b1.getComment(),comment.getDate(),b1.getRate());
        }
    }
}
