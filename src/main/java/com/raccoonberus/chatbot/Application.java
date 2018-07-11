package com.raccoonberus.chatbot;

import com.raccoonberus.chatbot.connector.ChatMessage;
import com.raccoonberus.chatbot.connector.telegram.TelegramClient;
import com.raccoonberus.chatbot.strategy.LazyReplyStrategy;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Dialog;
import com.vk.api.sdk.objects.messages.responses.GetDialogsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static ReplyStrategy[] strategies = {
            new LazyReplyStrategy(),
    };

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        /*Thread telegramThread = new Thread(new Runnable() {
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
        threads.add(telegramThread);*/

//        System.out.println(System.getenv("VKONTAKTE_GROUP_ID"));
//        System.out.println(System.getenv("VKONTAKTE_GROUP_KEY"));
//        System.exit(0);

        Thread vkontakteThread = new Thread(new Runnable() {
            @Override
            public void run() {
                TransportClient transportClient = HttpTransportClient.getInstance();
                VkApiClient vk = new VkApiClient(transportClient);
                GroupActor actor = new GroupActor(
                        Integer.parseInt(System.getenv("VKONTAKTE_GROUP_ID")),
                        System.getenv("VKONTAKTE_GROUP_KEY")
                );
                while (true) {
                    try {
                        GetDialogsResponse response = vk.messages().getDialogs(actor).execute();
                        for (Dialog d : response.getItems()) {
                            if (null != d.isUnanswered() && d.isUnanswered()) {
                                vk.messages().send(actor)
                                        .userId(d.getMessage().getUserId())
                                        .message(String.format(
                                                "Hello! " +
                                                        "Nice to meet you! " +
                                                        "Sorry, but command \"%s\" is unknown for me =(",
                                                d.getMessage().getBody()))
                                        .execute();
                            }
                        }
                    } catch (ApiException e) {
                        e.printStackTrace();
                    } catch (ClientException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        vkontakteThread.start();
        threads.add(vkontakteThread);


        for (Thread thread : threads) {
            thread.join();
        }
    }
}
