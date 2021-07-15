package com.bjtu.bookstore.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.bookstore.entity.*;
import com.bjtu.bookstore.mapper.*;
import com.bjtu.bookstore.service.CartService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartImpl implements CartService {
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private BookMapper bookMapper;

    // 查看购物车中所有图书，返回店铺和图书
    @Override
    public HashMap<String, Object> getAllCart(User user) {


        ArrayList<Store> stores = new ArrayList<>();
        stores=cartMapper.getAllCartStores(user.getId());
        for(int i=0;i<stores.size();i++) {
            ArrayList<Book> books = new ArrayList<>();
            books=cartMapper.getAllCartBooks(user.getId(),stores.get(i).getStoreId(),user.getState());
            for(int j=0;j<books.size();j++) {

                Cart cart;
                cart = cartMapper.getUserBookNum(user.getId(), books.get(j).getId());

                books.get(j).setStoreName(storeMapper.getNameById(stores.get(i).getId()));
                books.get(j).setCategoryName(categoryMapper.getNameById(books.get(j).getCategoryId()));
                books.get(j).setNum(cart.getNum());
            }
            if(books.size()==0) {
                stores.remove(i);
                i--;
            }
            else
                stores.get(i).setBooks(books);
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("storeList", stores);
        return data;
    }

    // 结算购物车
    @Override
    public int calculate(ArrayList<Book> books) {
        int price=0;

        for(int i=0;i<books.size();i++) {
            price+=bookMapper.getBookPrice(books.get(i).getId())*books.get(i).getNum();
        }

        return price;
    }

    // 加入购物车
    @Override
    public int addtocart(Cart cart) {
        int success=-1;

        for(int i=0;i<cart.getBooks().size();i++) {
            ArrayList<Cart> results = cartMapper.judge(cart.getUserId(), cart.getBooks().get(i).getId());
            if(results.size()>0)
                success=cartMapper.modifyNumFromCart(cart.getUserId(),cart.getBooks().get(i).getId(),cart.getBooks().get(i).getNum());
            else
                success=cartMapper.addtocart(cart.getUserId(),cart.getBooks().get(i).getId(),cart.getBooks().get(i).getNum());
        }
        return success;
    }

    // 从购物车中删除图书
    @Override
    public int deletefromcart(Cart cart) {
        int success=-1;
        for(int i=0;i<cart.getBooks().size();i++) {
            success=cartMapper.deletefromcart(cart.getUserId(),cart.getBooks().get(i).getId());
        }

        return success;
    }

    // 修改购物车中图书数目
    @Override
    public int modifyNumFromCart(Cart cart) {
        int success=-1;
        for(int i=0;i<cart.getBooks().size();i++) {
            int num=cart.getBooks().get(i).getNum();
            if(num==0)
                success=cartMapper.deletefromcart(cart.getUserId(),cart.getBooks().get(i).getId());
            else
                success=cartMapper.modifyNumFromCart(cart.getUserId(),cart.getBooks().get(i).getId(),num);
        }

        return success;
    }
}
