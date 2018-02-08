package com.liuyi.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.util.SerializationUtils;

public class HelloMapMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(SerializationUtils.deserialize(message.getBody()));
        System.out.println(bytes);
    }
}
