package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Lunbo;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.service.CartService;
import com.bjtu.bookstore.service.LunboService;
import com.bjtu.bookstore.service.UserService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/lunbo")
public class LunboController {
    @Autowired
    private LunboService lunboService;

    @PostMapping(value = "/getAllLunbos")
    public HashMap<String, Object> getAllLunbos() {

        return lunboService.getAllLunbos();
    }

    private final static String rootPath
            =System.getProperty("user.dir")+"\\src\\main\\resources\\static\\";
    @PostMapping(value = "/addToLunbo")
    public HashMap<String, Object> addtolunbo( Lunbo lunbo) {
        MultipartFile file=lunbo.getFile();
        System.out.println("文件上传后端测试");

        File fileDir = new File(rootPath);
        if(!fileDir.exists()&&!fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        try {
            if (file != null) {
                System.out.println("文件不空");
                String oldName = file.getOriginalFilename();
                System.out.println(oldName);
                String newName = UUID.randomUUID().toString() + (oldName != null ? oldName.substring(oldName.lastIndexOf(".")) : null);
                lunbo.setImgUrl("http://localhost:8090/static/"
                        +newName);
                file.transferTo(new File(rootPath, newName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lunbo.setLunboId(UUID.randomUUID().toString());
        lunboService.addtolunbo(lunbo);
        HashMap<String, Object> data = new HashMap<>();
        data.put("imgUrl", lunbo.getImgUrl());
        return data;
    }

    @PostMapping(value = "/deleteFromLunbo")
    public int deletefromlunbo(@RequestBody Lunbo lunbo) {
        Preconditions.checkNotNull(lunbo);

        return lunboService.deletefromlunbo(lunbo);
    }
}

