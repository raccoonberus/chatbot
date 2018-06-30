package com.raccoonberus.chatbot.connector;

import java.util.Date;

public interface ChatIncomingMessage {

    Date sentAt();

    String getSenderId();
    
    String getMessageText();
    
}
