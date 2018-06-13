package com.raccoonberus.chatbot.connector.telegram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TelegramClient {
    public String send(String message) {
        String token = System.getenv("TELEGRAM_BOT_TOKEN");
        String chatID = System.getenv("TELEGRAM_BOT_CHAT_ID");

        URL url;
        try {
            url = new URL("https://api.telegram.org/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        try {
            URLConnection yc = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                builder.append(inputLine);
            in.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
