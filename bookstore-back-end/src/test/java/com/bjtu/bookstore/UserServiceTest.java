package com.bjtu.bookstore;

import com.bjtu.bookstore.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @program: questionPlatform_back_end
 * @description: user service test
 * @author: CodingLiOOT
 * @create: 2021-03-25 18:27
 * @version: 1.0
 **/
public class UserServiceTest extends FrameworkApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void loginTest() {
        //System.out.println(userService.userLogin("Ljz123hhh", "Ljz123hhh",0));
    }

    @Test
    public void md5Test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456" + "CodingLiOOT" + "12345");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("123456" + "CodingLiOOT" + "12345", encode);
        System.out.println(matches);
    }

    @Test
    public void UUIDTest() {
        System.out.println(UUID.randomUUID());
    }
}
