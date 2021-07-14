package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Lunbo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface LunboMapper {
    //    查询轮播商品
    @Select("select * from lunbo")
    ArrayList<Lunbo> getAllLunbos();
    //    加入轮播商品
    @Insert("insert into lunbo values(#{lunboId},#{lunboName},#{lunboContent},#{imgUrl})")
    int addtolunbo(Lunbo lunbo);
    //    删除轮播商品
    @Delete("delete from lunbo where lunboId=#{lunboId}")
    int deletefromlunbo(String lunboId);
}
