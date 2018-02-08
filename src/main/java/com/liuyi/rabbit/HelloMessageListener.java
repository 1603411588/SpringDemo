package com.liuyi.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HelloMessageListener {

    @RabbitListener(queues = {RabbitMQConfig.test_hello_queue_name})
    public void helloMessageConsumer(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = {RabbitMQConfig.test_map_queue_name})
    public void mapMessageConsumer(Map<String, Object> msgMap) {
        System.out.println(msgMap);
    }
}
