package com.raccoonberus.chatbot;

import com.raccoonberus.chatbot.connector.ChatMessage;
import com.raccoonberus.chatbot.strategy.LazyReplyStrategy;
import com.raccoonberus.chatbot.connector.telegram.TelegramClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static ReplyStrategy[] strategies = {
            new LazyReplyStrategy(),
    };

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        Thread telegramThread = new Thread(new Runnable() {
            @Override
            public void run() {
                TelegramClient t = new TelegramClient(
                        System.getenv("TELEGRAM_BOT_TOKEN"),
                        System.getenv("TELEGRAM_PROXY")
                );
                while (true) {
                    List<ChatMessage> inbox = t.getInbox();
                    for (ChatMessage m : inbox) {
                        t.send(m.getSenderId(), String.format("I don't know what is mean \"%s\"", m.getMessageText()));
                    }
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        telegramThread.start();
        threads.add(telegramThread);

        //


        for (Thread thread : threads) {
            thread.join();
        }
    }
}
