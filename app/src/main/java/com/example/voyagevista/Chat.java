package com.example.voyagevista;

public class Chat {
    public String chatcontent, sentBy;

    public String getChatcontent() {
        return chatcontent;
    }

    public String getSentBy() {
        return sentBy;
    }

    public Chat(String chatcontent, String sentBy) {
        this.chatcontent = chatcontent;
        this.sentBy = sentBy;
    }
}
