package com.liuyi.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class HelloStringMessageListener implements MessageListener{

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message);
        System.out.println(bytes);
    }
}
