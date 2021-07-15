package com.bjtu.bookstore.mapper;

import com.bjtu.bookstore.entity.Role;
import com.bjtu.bookstore.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    // 返回所有正常用户或者是违规用户
    @Select("select * from user where state = #{state} LIMIT #{n}, #{m}")
    ArrayList<User> getRightUsers(int state, int n, int m);

    // 找到所有正常用户或者是违规用户的数目
    @Select("select count(*) from user where state=#{state}")
    int getRightNum(int state);

    // 管理员修改用户的状态
    @Update("update user set state=#{state} where id=#{id}")
    int changeUserState(int state, String id);

    @Select("select * from user where username = #{username}")
    User selectUserByUserName(String username);

    @Select(value = "select username from user where mail = #{mail} ")
    String selectUsernameByMail(String mail);

    @Select("select roleName from user,role,user_role " +
            "where user.username=#{username} " + "" +
            "and user.id=user_role.userid and role.id = user_role.roleid")
    List<Role> selectRoleByUserName(String username);

    @Select("select * from user where username = #{username} or mail = #{mail}")
    List<User> selectUserByNameOrMail(String username, String mail);

    @Select("select type from user where username = #{username} or mail = #{mail}")
    String selectTypeByUserName(String username);

    @Insert("insert into user (id,username,password,createdDate,mail,state,type) " +
            "values (#{id},#{username},#{password},NOW(),#{mail},1,1)")
    void register(User user);

    @Update("update user set password = #{password} where username = #{username}")
    void updatePassword(String username, String password);


    //通过用户id获取用户的用户名、注册日期，邮箱，用户头像地址，手机号，性别，生日
    @Select("<script>"
            + "select username,submission_date,mail,avatar,phone,gender,birthday from user where id=#{userId}"
            + "</script>"
    )
    User getInformation(String userId);


    //修改个人信息
    @Update("<script>"
            + "update user  "
            + "<set>"
            + " <if test=\"username != null\">username=#{username},</if>"
            + " <if test=\"birthday != null\">birthday=#{birthday},</if>"
            + " <if test=\"gender != null\">gender=#{gender},</if>"
            + " <if test=\"phone != null\">phone=#{phone}</if>"
            + "</set>"
            + "where id=#{id}"
            + "</script>"
    )
    void modifyInformation(User user);

}
