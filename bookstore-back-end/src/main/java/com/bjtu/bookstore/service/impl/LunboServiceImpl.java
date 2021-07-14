package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Lunbo;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.mapper.CategoryMapper;
import com.bjtu.bookstore.mapper.LunboMapper;
import com.bjtu.bookstore.service.LunboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
public class LunboServiceImpl implements LunboService {
    @Autowired
    private LunboMapper lunboMapper;


    @Override
    public HashMap<String, Object> getAllLunbos() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("lunboList", lunboMapper.getAllLunbos());
        return data;
    }

    @Override
    public void addtolunbo(Lunbo lunbo) {
        lunboMapper.addtolunbo(lunbo);
    }

    @Override
    public int deletefromlunbo(Lunbo lunbo) {
        return lunboMapper.deletefromlunbo(lunbo.getLunboId());
    }
}
