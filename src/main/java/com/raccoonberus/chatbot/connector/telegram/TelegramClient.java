package com.raccoonberus.chatbot.connector.telegram;

import com.raccoonberus.chatbot.connector.telegram.model.GetUpdatesResponse;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class TelegramClient {
    public String send(String message) {
        String token = System.getenv("TELEGRAM_BOT_TOKEN");
        String chatID = System.getenv("TELEGRAM_BOT_CHAT_ID");
        String proxyAddr = System.getenv("TELEGRAM_PROXY");

        try {
            URL url = new URL("https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + chatID + "&text=" + message);
//            URL url = new URL("https://api.telegram.org/bot" + token + "/getUpdates?chat_id=" + chatID);

            URL proxyUrl = new URL(proxyAddr);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUrl.getHost(), proxyUrl.getPort()));
            URLConnection yc = url.openConnection(proxy);
            yc.setDoOutput(true);
            yc.setDoInput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                builder.append(inputLine);
            in.close();
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public GetUpdatesResponse getUpdates() {
        String token = System.getenv("TELEGRAM_BOT_TOKEN");
        String chatID = System.getenv("TELEGRAM_BOT_CHAT_ID");
        String proxyAddr = System.getenv("TELEGRAM_PROXY");

        Client client = ClientBuilder.newClient();
        client.property(ClientProperties.PROXY_URI, proxyAddr);
        GetUpdatesResponse response = client.target("https://api.telegram.org/bot" + token + "/")
                .path("getUpdates")
                .queryParam("chat_id", chatID)
                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(null, MediaType.APPLICATION_JSON), GetUpdatesResponse.class);
                .get(GetUpdatesResponse.class);

        System.out.println(response.isOk() ? "OK" : "Something wrong!");
        if (response.getResult().length > 0)
            System.out.println(response.getResult()[0].getUpdateId());

        return null;
    }
}

