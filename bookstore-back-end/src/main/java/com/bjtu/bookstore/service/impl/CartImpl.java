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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
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

        ArrayList<Book> books = new ArrayList<>();
        books = cartMapper.getAllCartBooks(user.getId());

        HashMap<String, Object> data = new HashMap<>();
        data.put("shops", books);
        return data;
    }

    // 结算购物车
    @Override
    public int calculate(JSONObject object) {
        int price=-1;

        JSONArray bookList=object.getJSONArray("bookList");
        for(int i=0;i<bookList.size();i++) {
            String bookid = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getString("bookId");
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

        int success = cartMapper.addtocart(userid,bookid,num);
        return success;
    }

    // 从购物车中删除图书
    @Override
    public int deletefromcart(JSONObject object) {
        String userid=object.getString("ID");
        JSONArray jsonArray=object.getJSONArray("bookList");

        int success=-1;
        for(int i=0;i<jsonArray.size();i++) {
            String bookid = JSONObject.parseObject(JSONObject.toJSONString(jsonArray.get(i))).getString("bookId");
            success=cartMapper.deletefromcart(userid,bookid);
        }

        return success;
    }

    // 修改购物车中图书数目
    @Override
    public int modifyNumFromCart(JSONObject object) {
        String userid=object.getString("ID");
        JSONArray bookList =object.getJSONArray("bookList");

        int success=-1;

        for(int i=0;i<bookList.size();i++) {
            String bookid = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getString("bookId");
            int num = JSONObject.parseObject(JSONObject.toJSONString(bookList.get(i))).getInteger("num");
            if(num==0)
                success=cartMapper.deletefromcart(userid,bookid);
            else
                success=cartMapper.modifyNumFromCart(userid,bookid,num);
        }

        return success;
    }
}
