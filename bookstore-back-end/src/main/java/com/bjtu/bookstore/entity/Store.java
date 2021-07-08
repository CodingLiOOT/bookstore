package com.bjtu.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: bookstore-back-end
 * @description: store entity
 * @author: CodingLiOOT
 * @create: 2021-07-08 15:54
 * @version: 1.0
 **/
@Data
public class Store {

    private String id;

    private String userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdTime;

    private String name;

    private String desc;

    private Integer state;

    private List<Book> books;
}
