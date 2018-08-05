package com.raccoonberus.chatbot.strategy;

import com.raccoonberus.chatbot.ReplyStrategy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LifeMeaningReplyStrategy implements ReplyStrategy {

    private static final String keywords = "смысл жизни meaning life";

    @Override
    public boolean isSupport(String message) {
        String s = message.toLowerCase()
                .replaceAll("/[\\W]+/u", "")
                .replaceAll("\\?", "")
                ;
        Set<String> set2 = new HashSet<>(Arrays.asList(s.split(" ")));
        Set<String> set1 = new HashSet<>(Arrays.asList(keywords.toLowerCase().split(" ")));
        set1.retainAll(set2);
        return set1.size() >= 2;
    }

    @Override
    public boolean isSupport(String... keywords) {
        return false;
    }

    @Override
    public String run(String message) {
        return "The meaning of life is to refrain from working with PHP!";
    }

}
