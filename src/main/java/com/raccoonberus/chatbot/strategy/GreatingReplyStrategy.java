package com.raccoonberus.chatbot.strategy;

import com.raccoonberus.chatbot.ReplyStrategy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GreatingReplyStrategy implements ReplyStrategy {


    private static final String keywords = "hello,hi,aloha,привет,хай,здравствуй";

    @Override
    public boolean isSupport(String message) {
        Set<String> set2 = new HashSet<>(Arrays.asList(message.toLowerCase().split(" ")));
        Set<String> set1 = new HashSet<>(Arrays.asList(keywords.toLowerCase().split(",")));
        set1.retainAll(set2);
        return set1.size() > 0;
    }

    @Override
    public boolean isSupport(String... keywords) {
        return false;
    }

    @Override
    public String run(String message) {
        return "Hello!!!";
    }

}
