package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Lunbo;

import java.util.HashMap;

public interface LunboService {
    HashMap<String, Object> getAllLunbos();

    HashMap<String, Object> addtolunbo(Lunbo lunbo);

    int deletefromlunbo(Lunbo lunbo);

    HashMap<String, Object> getOneLunbo(Lunbo lunbo);
}
