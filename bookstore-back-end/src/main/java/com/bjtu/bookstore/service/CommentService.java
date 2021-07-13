package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Comment;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface CommentService {
    public HashMap<String, Object> getCommentByBook(Comment comment);
    public HashMap<String, Object> getCommentByOrder(Comment comment);
    public void sendComment(Comment comment);
}
