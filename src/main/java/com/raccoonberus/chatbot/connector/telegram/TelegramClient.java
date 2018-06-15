package com.raccoonberus.chatbot.connector.telegram;

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
//            URL url = new URL("https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + chatID + "&text=" + message);
            URL url = new URL("https://api.telegram.org/bot" + token + "/getUpdates?chat_id=" + chatID);

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

    public class Employee {
        private int id;
        private String firstName;

        // standard getters and setters
    }

    Client client = ClientBuilder.newClient();

    WebTarget webTarget = client.target("http://localhost:8082/spring-jersey");

    WebTarget employeeWebTarget = webTarget.path("resources/employees");

    Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION.JSON);

    Response response = invocationBuilder.get(Employee.class);

    Response response = invocationBuilder.post(Entity.entity(employee, MediaType.APPLICATION_JSON));

    public class RestClient {

        private static final String REST_URI = "http://localhost:8082/spring-jersey/resources/employees";

        private Client client = ClientBuilder.newClient();

        public Employee getJsonEmployee(int id) {
            return client
                    .target(REST_URI)
                    .path(String.valueOf(id))
                    .request(MediaType.APPLICATION_JSON)
                    .get(Employee.class);
        }
        //...
    }

    public Response createJsonEmployee(Employee emp) {
        return client
                .target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(emp, MediaType.APPLICATION_JSON));
    }

    public class JerseyClientLiveTest {

        public static final int HTTP_CREATED = 201;
        private RestClient client = new RestClient();

        @Test
        public void givenCorrectObject_whenCorrectJsonRequest_thenResponseCodeCreated() {
            Employee emp = new Employee(6, "Johny");

            Response response = client.createJsonEmployee(emp);

            assertEquals(response.getStatus(), HTTP_CREATED);
        }
    }
}

