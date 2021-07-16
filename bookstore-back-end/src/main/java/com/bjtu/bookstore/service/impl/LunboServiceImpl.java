package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Lunbo;
import com.bjtu.bookstore.mapper.LunboMapper;
import com.bjtu.bookstore.service.LunboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public HashMap<String, Object> addtolunbo(Lunbo lunbo) {

        lunbo.setLunboId(UUID.randomUUID().toString());
        String url = lunbo.getImgUrl();
        int i = url.indexOf("?");
        lunbo.setImgUrl(url.substring(0, i));
        lunboMapper.addtolunbo(lunbo.getLunboId(), lunbo.getLunboName(), lunbo.getImgUrl());
        HashMap<String, Object> data = new HashMap<>();
        data.put("imgUrl", lunbo.getImgUrl());
        return data;
    }

    @Override
    public int deletefromlunbo(Lunbo lunbo) {
        return lunboMapper.deletefromlunbo(lunbo.getLunboId());
    }

    @Override
    public HashMap<String, Object> getOneLunbo(Lunbo lunbo) {
        Lunbo l1 = lunboMapper.getOneLunbo(lunbo.getLunboId());
        HashMap<String, Object> data = new HashMap<>();
        data.put("lunbo", l1);
        return data;
    }
}
