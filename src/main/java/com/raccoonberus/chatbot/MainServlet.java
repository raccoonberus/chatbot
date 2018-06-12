package com.raccoonberus.chatbot;

import com.raccoonberus.chatbot.connector.telegram.TelegramClient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = resp.getWriter();
        writer.print("Test message 2!!!");

        writer.print(
                new TelegramClient().send("kolya")
        );
    }
}
