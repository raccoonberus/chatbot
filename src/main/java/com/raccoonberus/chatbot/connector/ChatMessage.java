package com.raccoonberus.chatbot.connector;

import java.util.Date;

public interface ChatMessage {

    Date sentAt();

    String getSenderId();
    
    String getMessageText();
    
}
