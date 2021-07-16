package com.bjtu.bookstore.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Comment {
    private String commentId;
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookName;
    private String orderId;
    private String comment;
    private Date date;
    private int rate;
    private ArrayList<Book> bookList;
}
