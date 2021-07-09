package com.bjtu.bookstore.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Store;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.mapper.CartMapper;
import com.bjtu.bookstore.mapper.UserMapper;
import com.bjtu.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartImpl implements CartService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private BookMapper bookMapper;

    // 查看购物车中所有图书，返回店铺和图书
    @Override
    public HashMap<String, Object> getAllCart(User user) {

        ArrayList<Store> stores=new ArrayList<>();
        System.out.println(user.getID());
        stores = cartMapper.getAllCartStores(user.getID());
        for (int i = 0; i <stores.size() ; i++) {
            ArrayList<Book> books = new ArrayList<>();
            books = cartMapper.getAllCartBooksOfStores(user.getID(), stores.get(i).getId());
            stores.get(i).setBooks(books);
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("shops", stores);
        return data;
    }

    // 结算购物车
    @Override
    public int calculate(JSONObject object) {

        String str=object.toJSONString();
        JSONObject json = JSONObject.parseObject(str);
        String bookstring=json.getString("bookList");

        int price=-1;

        JSONArray bookList=JSONArray.parseArray(bookstring);
        for(int i=0;i<bookList.size();i++) {
            String bookid = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getString("booId");
            int num = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getInteger("num");
            price+=bookMapper.getBookPrice(bookid)*num;
        }

        return price;
    }

    // 加入购物车
    @Override
    public int addtocart(Cart cart) {
        String userid=cart.getUserid();
        String bookid=cart.getBookid();
        int num=cart.getNum();

        String storeid=bookMapper.getStoreOfBook(bookid);
        int success = cartMapper.addtocart(userid,bookid,storeid,num);
        return 0;
    }

    // 从购物车中删除图书
    @Override
    public int deletefromcart(JSONObject object) {
        String str=object.toJSONString();
        JSONObject json = JSONObject.parseObject(str);
        String userid=json.getString("ID");
        String bookstring=json.getString("bookList");

        int success=-1;
        JSONArray bookList=JSONArray.parseArray(bookstring);
        for(int i=0;i<bookList.size();i++) {
            String bookid = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getString("booId");
            success=cartMapper.deletefromcart(userid,bookid);
        }

        return success;
    }

    // 修改购物车中图书数目
    @Override
    public int modifyNumFromCart(JSONObject object) {
        String str=object.toJSONString();
        JSONObject json = JSONObject.parseObject(str);
        String userid=json.getString("ID");
        String bookstring=json.getString("bookList");

        int success=-1;
        JSONArray bookList=JSONArray.parseArray(bookstring);
        for(int i=0;i<bookList.size();i++) {
            String bookid = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getString("booId");
            int num = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getInteger("num");
            if(num==0)
                success=cartMapper.deletefromcart(userid,bookid);
            else
                success=cartMapper.modifyNumFromCart(userid,bookid,num);
        }

        return success;
    }
}
