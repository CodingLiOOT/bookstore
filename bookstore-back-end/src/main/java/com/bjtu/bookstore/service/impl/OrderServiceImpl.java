package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Order;
import com.bjtu.bookstore.entity.Order_book;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.mapper.CategoryMapper;
import com.bjtu.bookstore.mapper.OrderMapper;
import com.bjtu.bookstore.mapper.Order_bookMapper;
import com.bjtu.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Order_bookMapper order_bookMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<HashMap<String,Object>> getOrderList(Order order) {
        List<HashMap<String,Object>>  list=new ArrayList<>();

        List<Order> orderList = orderMapper.getOrderList(order);
        System.out.println(orderList.size());

        for(int i=0;i<orderList.size();i++){
            HashMap<String,Object>  map=new HashMap<>();
            Order order1 = orderList.get(i);
            map.put("orderId",order1.getId());
            map.put("date",order1.getCreatedTime());
            map.put("sum",order1.getTotalPrice());
            map.put("shippingId",order1.getShippingId());
            map.put("state",order1.getState());
            int num=0;
            List<Order_book> byOrderId = order_bookMapper.getByOrderId(order1.getId());
            List<HashMap<String,Object>>  detailData=new ArrayList<>();
            for(int j=0;j<byOrderId.size();j++){
                HashMap<String,Object> datadetailItem =new HashMap<>();
                Order_book order_book = byOrderId.get(j);
                String bookId = order_book.getBookId();
                ArrayList<Book> detail = bookMapper.getDetail(bookId);
                Book book = detail.get(0);
                num+=order_book.getAmount();

                datadetailItem.put("bookName",book.getName());
                datadetailItem.put("bookId",book.getId());
                datadetailItem.put("shopName","bookstore");
                datadetailItem.put("category",categoryMapper.getNameById(book.getCategoryId()));
                datadetailItem.put("price",book.getPrice());
                datadetailItem.put("bookNum",order_book.getAmount());

                detailData.add(datadetailItem);
            }

            map.put("num",num);
            map.put("detailData",detailData);
            list.add(map);
        }

        return list;
    }

    @Override
    public void changestate(Order order) {
          orderMapper.changestate(order.getId(), order.getState());
    }

    @Override
    public HashMap<String,Object> confirmOrder(Order order) {

         order.setId(UUID.randomUUID().toString());
         order.setState(1);
         order.setCreatedTime(new Timestamp(new Date().getTime()));

         orderMapper.confirmOrder(order);

        List<Book> bookList = order.getBookList();
        for (Book book : bookList) {

            Order_book order_book=new Order_book();
            order_book.setOrderId(order.getId());
            order_book.setBookId(book.getId());
            order_book.setAmount(book.getNum());
            order_book.setStoreId("1");

            order_bookMapper.confirmOrderBook(order_book);

            bookMapper.updateDealNum(book.getId());
        }
        HashMap<String,Object> datas = new HashMap<>();
        datas.put("orderId", order.getId());
        return datas;
    }
}
