package com.raccoonberus.chatbot;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void getMessage() {

    }

    @Test
    public void check_getClassesFromConfig() {
        String[] classes = Application.getClassesFromConfig("one;two::three,four");
        assertEquals(4, classes.length);
        assertEquals("one", classes[0]);
        assertEquals("four", classes[3]);
    }
}