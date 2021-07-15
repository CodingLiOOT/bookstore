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

    private String storeId;

    private String userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdTime;

    private String storeName;

    private String desc;

    private Integer state;

    private List<Book> books;

    public String getId() {
        return storeId;
    }

    public void setId(String id) {
        this.storeId = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return storeName;
    }

    public void setName(String name) {
        this.storeName = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
