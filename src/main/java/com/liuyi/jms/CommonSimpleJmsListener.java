package com.liuyi.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommonSimpleJmsListener {

    @JmsListener(destination = "sboot.jms.simpletext")
    public void receiveSimpleText(String text) {
        System.out.println(text);
    }

    @JmsListener(destination = "sboot.jms.simplepojo")
    public void receiveSimplePojo(Email email) {
        System.out.println(email);
    }
}
