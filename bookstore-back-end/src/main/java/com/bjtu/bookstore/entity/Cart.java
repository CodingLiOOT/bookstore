package com.bjtu.bookstore.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Cart {

    private String cartId;

    private String userId;

    private ArrayList<Book> books;

    private ArrayList<Store> stores;

    private Double totalPrice;

    private int num;
}
