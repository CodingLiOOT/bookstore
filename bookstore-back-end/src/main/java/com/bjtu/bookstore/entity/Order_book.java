package com.bjtu.bookstore.entity;

import lombok.Data;

@Data
public class Order_book {
    private String orderId;

    private Long bookId;

    private Integer amount;

    private String storeId;

    private int state;
}
