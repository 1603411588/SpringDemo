package com.liuyi.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.HashMap;


@Configuration
public class RabbitMQConfig {

    public static final String test_hello_queue_name = "sboot_test_hello_queue_name";
    public static final String test_map_queue_name = "sboot_test_map_queue_name";
    public static final String sboot_exchange = "sboot_exchange";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(sboot_exchange);
    }

    @Bean
    Queue queue() {
        return new Queue(test_hello_queue_name, false);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(test_hello_queue_name);
    }

    @Bean
    Queue queue2() {
        return new Queue(test_map_queue_name, false);
    }

    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(test_map_queue_name);
    }

    @Bean
    @Order(3)
    CommandLineRunner runner3() {
        return agrs -> {
            rabbitTemplate.convertAndSend(sboot_exchange, test_hello_queue_name, "Hello Rabbit!!!");
            System.out.println(".....................................");
            HashMap<String, Object> msgMap = new HashMap<>();
            msgMap.put("name", "刘毅");
            rabbitTemplate.convertAndSend(sboot_exchange, test_map_queue_name, msgMap);
        };

    }
}
