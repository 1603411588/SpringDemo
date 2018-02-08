package com.liuyi.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisMessageController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/stringmsg/{msg}")
    public void sendRedisStringMsg(@PathVariable String msg) {
        stringRedisTemplate.convertAndSend(RedisChannelConstants.Hello, msg);
    }

    @RequestMapping("/mapmsg/")
    public void sendRedisMapMsg() {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "liuyi");
        redisTemplate.convertAndSend(RedisChannelConstants.Hello_Map, map);
    }
}
