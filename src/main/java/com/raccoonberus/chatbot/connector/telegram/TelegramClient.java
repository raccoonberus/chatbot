package com.raccoonberus.chatbot.connector.telegram;

import com.raccoonberus.chatbot.connector.ChatConnector;
import com.raccoonberus.chatbot.connector.ChatMessage;
import com.raccoonberus.chatbot.connector.telegram.model.GetUpdatesResponse;
import com.raccoonberus.chatbot.connector.telegram.model.ResultItem;
import com.raccoonberus.chatbot.connector.telegram.model.SendMessageResponse;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelegramClient implements ChatConnector {
    private final String baseUrl;
    private final Client client;

    private List<Long> updateId = new ArrayList<>();

    public TelegramClient(String token, String proxyAddr) {
        this.baseUrl = "https://api.telegram.org/bot" + token + "/";
        this.client = createTelegramClient(proxyAddr);
    }

    public void send(String chatId, String textMessage) {
        Map<String, Object> params = new HashMap<>();
        params.put("chat_id", chatId);
        params.put("text", textMessage);
        SendMessageResponse response = callMethod("sendMessage", params, SendMessageResponse.class);

        System.out.println(">>> " + response.getResult().getMessageId());
    }

    public List<ChatMessage> getInbox() {
        GetUpdatesResponse response = callMethod("getUpdates", null, GetUpdatesResponse.class);

        List<ChatMessage> messages = new ArrayList<>();
        for (ResultItem item : response.getResult()) {
            if (updateId.contains(item.getUpdateId()))
                continue;
            messages.add(item.getMessage());
            updateId.add(item.getUpdateId());
        }

        return messages;
    }

    private Client createTelegramClient(String proxyAddr) {
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.PROXY_URI, proxyAddr);
        config.connectorProvider(new ApacheConnectorProvider());
        config.property(ClientProperties.CONNECT_TIMEOUT, 10 * 1000);
        config.property(ClientProperties.READ_TIMEOUT, 10 * 1000);
        return ClientBuilder.newClient(config);
    }

    private <T> T callMethod(String methodName, Map<String, Object> params, Class<T> responseType) {
        WebTarget target = client.target(baseUrl).path(methodName);

        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                target = target.queryParam(entry.getKey(), entry.getValue());
            }
        }

        T response = target
                .request(MediaType.APPLICATION_JSON)
                .get(responseType);

        // log data

        return response;
    }
}

