package com.raccoonberus.chatbot.strategy;

import org.junit.Test;

import static org.junit.Assert.*;

public class LifeMeaningReplyStrategyTest {

    @Test
    public void isSupport() {
        LifeMeaningReplyStrategy subject = new LifeMeaningReplyStrategy();
        assertTrue(subject.isSupport("в чем смысл жизни?"));
    }
}