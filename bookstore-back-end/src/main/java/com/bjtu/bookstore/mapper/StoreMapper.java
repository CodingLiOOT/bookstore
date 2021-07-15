package com.bjtu.bookstore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StoreMapper {
    @Select("select storeName from store where storeId=#{id} ")
    String getNameById(String id);
}
