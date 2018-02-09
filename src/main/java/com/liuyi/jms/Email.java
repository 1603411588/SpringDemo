package com.liuyi.jms;

public class Email {

    private String to;
    private String content;

    public Email() {
    }

    public Email(String to, String content) {
        this.to = to;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
