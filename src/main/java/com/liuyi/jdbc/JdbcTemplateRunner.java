package com.liuyi.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Order(2)
@Component
public class JdbcTemplateRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(JdbcTemplateRunner.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        log.info("......Create tables......");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS ");
        jdbcTemplate.execute("CREATE TABLE customers (id SERIAL , name VARCHAR(255))");

        List<Object[]> list = Arrays.asList("Tom", "Jack", "Jerry").stream().map(name -> {
            return new String[]{name};
        }).collect(Collectors.toList());

        list.forEach(name -> {
            log.info(String.format("Inserting customer name is %s", name[0]));
        });

        jdbcTemplate.batchUpdate("INSERT INTO customers(name) VALUES(?) ", list);

        log.info("......Starting Query......");
        jdbcTemplate.query("SELECT id,name FROM customers ", new Object[]{}, (rs, rowNum) -> {
            return new Customer(rs.getLong("id"), rs.getString("name"));
        }).forEach(customer -> log.info(customer.toString()));
    }
}
