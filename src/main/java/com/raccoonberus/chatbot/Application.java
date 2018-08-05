package com.raccoonberus.chatbot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Dialog;
import com.vk.api.sdk.objects.messages.responses.GetDialogsResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Application {

    private static final StrategiesRegistry REGISTRY = new StrategiesRegistry();

    public static void main(String[] args) throws InterruptedException {

//        System.out.println(new File("chatbot.properties").getAbsoluteFile());
//        System.exit(-1);

        Properties properties = new Properties();
        if (System.getProperty("external.properties.file") == null) {
            System.err.println("You should define \"external.properties.file\" property and write in file strategies list");
            System.exit(-1);
        }
        try {
//            properties.load(new FileInputStream(System.getProperty("external.properties.file")));
            properties.load(new FileInputStream(
                    new File(System.getProperty("external.properties.file")).getAbsoluteFile()
            ));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        for (String className : getClassesFromConfig(properties.getProperty("chatbot.strategies"))) {
            REGISTRY.add(className);
        }

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
                                String query = d.getMessage().getBody().toLowerCase();
                                String answer = REGISTRY.resolveAndReply(query);

                                vk.messages().send(actor)
                                        .userId(d.getMessage().getUserId())
                                        .message(answer)
                                        .execute();
                                System.out.println(d.getMessage().getUserId() + " <- " + answer);
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

    public static String[] getClassesFromConfig(String line) {
        List<String> list = new LinkedList<>(Arrays.asList(
                line.split("[;,:]")
        ));
        list.removeAll(Arrays.asList("", null));
        return list.toArray(new String[]{});
    }
}
