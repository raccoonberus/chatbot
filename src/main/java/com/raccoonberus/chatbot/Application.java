package com.raccoonberus.chatbot;

import com.raccoonberus.chatbot.strategy.LazyReplyStrategy;

public class Application {
    private static ReplyStrategy[] strategies = {
            new LazyReplyStrategy(),
    };

    public static void main(String[] args) {
//        System.out.print(getMessage());


    }

    private static String answer(String message) {
        for (ReplyStrategy strategy :
                strategies) {
            if (strategy.isSupport(message)) {
                return strategy.run(message);
            }
        }

        return "Sorry, I don't understand you =)";
    }

    public static String getMessage() {
        return "Hello from ChatBot";
    }
}
