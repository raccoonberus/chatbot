package com.raccoonberus.chatbot.strategy;

import com.raccoonberus.chatbot.ReplyStrategy;

public class LazyReplyStrategy implements ReplyStrategy {
    @Override
    public boolean isSupport(String message) {
        return true;
    }

    @Override
    public boolean isSupport(String... keywords) {
        return true;
    }

    @Override
    public String run(String message) {
        return "Sorry, I have a lunch break! Go out!!!";
    }
}
