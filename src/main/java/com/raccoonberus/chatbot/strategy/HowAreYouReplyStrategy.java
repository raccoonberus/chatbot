package com.raccoonberus.chatbot.strategy;

import com.raccoonberus.chatbot.ReplyStrategy;

public class HowAreYouReplyStrategy implements ReplyStrategy {

    private static final String keywords0 = "how are you";

    @Override
    public boolean isSupport(String message) {
        return message.toLowerCase().contains(keywords0.toLowerCase());
    }

    @Override
    public boolean isSupport(String... keywords) {
        return false;
    }

    @Override
    public String run(String message) {
        return "Thanks!\nI'm fine!";
    }

}
