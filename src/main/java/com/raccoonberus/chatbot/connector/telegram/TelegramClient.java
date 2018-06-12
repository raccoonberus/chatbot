package com.raccoonberus.chatbot.connector.telegram;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class TelegramClient {
    public String send(String message) {
        String token = System.getenv("TELEGRAM_BOT_TOKEN");
        String chatID = System.getenv("TELEGRAM_BOT_CHAT_ID");

        Client client = ClientBuilder.newClient();
        String name = client.target("https://api.telegram.org/bot" + token + "/")
                .path("sendMessage")
                .queryParam("chat_id", chatID)
                .queryParam("text", message)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(String.class), String.class);
        return name;
    }
}
