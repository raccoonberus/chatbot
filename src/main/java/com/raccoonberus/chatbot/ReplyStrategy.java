package com.raccoonberus.chatbot;

public interface ReplyStrategy {
    boolean isSupport(String message);

    boolean isSupport(String... keywords);

    String run(String message);
}
