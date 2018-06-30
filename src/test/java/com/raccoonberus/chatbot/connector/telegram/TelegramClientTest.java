package com.raccoonberus.chatbot.connector.telegram;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoonberus.chatbot.connector.telegram.model.GetUpdatesResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TelegramClientTest {

    @Test
    public void parseGetUpdates() throws IOException {
       String response = "{\n" +
                "  \"ok\": true,\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"update_id\": 934773487,\n" +
                "      \"message\": {\n" +
                "        \"message_id\": 22,\n" +
                "        \"from\": {\n" +
                "          \"id\": 360663876,\n" +
                "          \"is_bot\": false,\n" +
                "          \"first_name\": \"Sergey\",\n" +
                "          \"last_name\": \"Cherkesov\",\n" +
                "          \"username\": \"go_for_broke\",\n" +
                "          \"language_code\": \"en-US\"\n" +
                "        },\n" +
                "        \"chat\": {\n" +
                "          \"id\": 360663876,\n" +
                "          \"first_name\": \"Sergey\",\n" +
                "          \"last_name\": \"Cherkesov\",\n" +
                "          \"username\": \"go_for_broke\",\n" +
                "          \"type\": \"private\"\n" +
                "        },\n" +
                "        \"date\": 1530372162,\n" +
                "        \"text\": \"ghbdtn\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"update_id\": 934773488,\n" +
                "      \"message\": {\n" +
                "        \"message_id\": 23,\n" +
                "        \"from\": {\n" +
                "          \"id\": 360663876,\n" +
                "          \"is_bot\": false,\n" +
                "          \"first_name\": \"Sergey\",\n" +
                "          \"last_name\": \"Cherkesov\",\n" +
                "          \"username\": \"go_for_broke\",\n" +
                "          \"language_code\": \"en-US\"\n" +
                "        },\n" +
                "        \"chat\": {\n" +
                "          \"id\": 360663876,\n" +
                "          \"first_name\": \"Sergey\",\n" +
                "          \"last_name\": \"Cherkesov\",\n" +
                "          \"username\": \"go_for_broke\",\n" +
                "          \"type\": \"private\"\n" +
                "        },\n" +
                "        \"date\": 1530372199,\n" +
                "        \"text\": \"efweew\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"update_id\": 934773489,\n" +
                "      \"message\": {\n" +
                "        \"message_id\": 24,\n" +
                "        \"from\": {\n" +
                "          \"id\": 360663876,\n" +
                "          \"is_bot\": false,\n" +
                "          \"first_name\": \"Sergey\",\n" +
                "          \"last_name\": \"Cherkesov\",\n" +
                "          \"username\": \"go_for_broke\",\n" +
                "          \"language_code\": \"en-US\"\n" +
                "        },\n" +
                "        \"chat\": {\n" +
                "          \"id\": 360663876,\n" +
                "          \"first_name\": \"Sergey\",\n" +
                "          \"last_name\": \"Cherkesov\",\n" +
                "          \"username\": \"go_for_broke\",\n" +
                "          \"type\": \"private\"\n" +
                "        },\n" +
                "        \"date\": 1530373822,\n" +
                "        \"text\": \"dbdfbfd\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        GetUpdatesResponse r = mapper.readValue(response, GetUpdatesResponse.class);
        System.out.println(r);

        assertTrue(r.isOk());
        assertEquals(3, r.getResult().size());
    }

}