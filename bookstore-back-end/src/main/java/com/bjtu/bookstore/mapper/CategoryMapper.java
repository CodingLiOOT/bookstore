package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    @Select("select * from category")
    List<Category> getCategories();


    @Select("select name from category where id=#{id} ")
    String getNameById(String id);
}
