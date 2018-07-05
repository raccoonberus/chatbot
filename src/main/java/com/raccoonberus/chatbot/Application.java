package com.raccoonberus.chatbot;

import com.raccoonberus.chatbot.connector.ChatMessage;
import com.raccoonberus.chatbot.strategy.LazyReplyStrategy;
import com.raccoonberus.chatbot.connector.telegram.TelegramClient;

import java.io.IOException;
import java.util.List;

public class Application {
    private static ReplyStrategy[] strategies = {
            new LazyReplyStrategy(),
    };

    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.print(getMessage());
        TelegramClient t = new TelegramClient(
                System.getenv("TELEGRAM_BOT_TOKEN"),
                System.getenv("TELEGRAM_PROXY")
        );

        while (true) {
            List<ChatMessage> inbox = t.getInbox();
            for (ChatMessage m : inbox) {
                t.send(m.getSenderId(), String.format("I don't know what is mean \"%s\"", m.getMessageText()));
            }
            Thread.sleep(5 * 1000);
        }
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
