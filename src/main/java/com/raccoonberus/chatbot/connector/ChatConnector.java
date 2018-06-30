package com.raccoonberus.chatbot.connector;

public interface ChatConnector {

    void send(String receiverId, String textMessage);

    void getInbox();

}
