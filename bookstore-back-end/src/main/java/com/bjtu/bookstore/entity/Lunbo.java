package com.bjtu.bookstore.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Lunbo {
    private String lunboId;
    private String lunboName;
    private String lunboContent;
    private MultipartFile file;
    private String imgUrl;
}
