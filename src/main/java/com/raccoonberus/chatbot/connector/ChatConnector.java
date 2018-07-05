package com.raccoonberus.chatbot.connector;

import java.util.List;

public interface ChatConnector {

    void send(String chatId, String textMessage);

    List<ChatMessage> getInbox();

}
