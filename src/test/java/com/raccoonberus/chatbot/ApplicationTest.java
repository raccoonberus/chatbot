package com.raccoonberus.chatbot;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void getMessage() {
        assertEquals("Simple test",
                "Hello from ChatBot", Application.getMessage());
    }
}