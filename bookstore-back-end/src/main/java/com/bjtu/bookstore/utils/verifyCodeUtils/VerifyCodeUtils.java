package com.bjtu.bookstore.utils.verifyCodeUtils;

import com.bjtu.bookstore.utils.redis.JedisInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @program: questionPlatform_back_end
 * @description: verify code tools
 * @author: CodingLiOOT
 * @create: 2021-03-28 16:28
 * @version: 1.0
 **/
@Slf4j
@Component
public class VerifyCodeUtils {

    public String generateCode(String key) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        String code = str.toString();
        Jedis jedis = JedisInstance.getInstance().getResource();
        jedis.setex(key, 300, code);
        jedis.close();
        return code;
    }

    public boolean verifyCode(String key, String value) {
        Jedis jedis = JedisInstance.getInstance().getResource();

        if (!value.equals(jedis.get(key))) {
//            throw new DefinitionException(ErrorEnum.ERROR_VERIFY_CODE);
            jedis.close();
            return false;
        }
        jedis.del(key);
        jedis.close();
        return true;
    }

}
