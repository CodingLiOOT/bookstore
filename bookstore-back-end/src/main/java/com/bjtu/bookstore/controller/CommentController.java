package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Comment;
import com.bjtu.bookstore.service.BookService;
import com.bjtu.bookstore.service.CommentService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/getCommentByBook")
    public HashMap<String, Object> getCommentByBook(@RequestBody Comment comment) {
        Preconditions.checkNotNull(comment);

        return commentService.getCommentByBook(comment);
    }

    @PostMapping(value = "/getCommentByOrder")
    public HashMap<String, Object> getCommentByOrder(@RequestBody Comment comment) {
        Preconditions.checkNotNull(comment);

        return commentService.getCommentByOrder(comment);
    }

    @PostMapping(value = "/sendComment")
    public void sendComment(@RequestBody Comment comment) {
        Preconditions.checkNotNull(comment);

        commentService.sendComment(comment);
    }
}
