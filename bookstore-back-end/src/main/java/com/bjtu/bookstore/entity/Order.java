package com.bjtu.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: bookstore-back-end
 * @description: order entity
 * @author: CodingLiOOT
 * @create: 2021-07-08 15:48
 * @version: 1.0
 **/
@Data
public class Order {

    private String id;

    private String shippingId;

    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp completedTime;

    private Double totalPrice;

    private String payType;

    private Integer state;

    private List<Book> bookList;
}
