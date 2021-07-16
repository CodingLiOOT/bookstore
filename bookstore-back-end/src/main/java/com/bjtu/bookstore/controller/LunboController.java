package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Lunbo;
import com.bjtu.bookstore.service.LunboService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
            = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";

    @PostMapping(value = "/addToLunbo")
    public HashMap<String, Object> addtolunbo(@RequestBody Lunbo lunbo) {
        return lunboService.addtolunbo(lunbo);
    }

    @PostMapping(value = "/deleteFromLunbo")
    public int deletefromlunbo(@RequestBody Lunbo lunbo) {
        Preconditions.checkNotNull(lunbo);

        return lunboService.deletefromlunbo(lunbo);
    }

    @PostMapping(value = "/getOneLunbo")
    public HashMap<String, Object> getOneLunbo(@RequestBody Lunbo lunbo) {
        return lunboService.getOneLunbo(lunbo);
    }
}

