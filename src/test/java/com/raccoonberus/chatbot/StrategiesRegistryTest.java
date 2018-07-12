package com.raccoonberus.chatbot;

import com.raccoonberus.chatbot.strategy.GreatingReplyStrategy;
import com.raccoonberus.chatbot.strategy.HowAreYouReplyStrategy;
import com.raccoonberus.chatbot.strategy.LazyReplyStrategy;
import com.raccoonberus.chatbot.strategy.LifeMeaningReplyStrategy;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrategiesRegistryTest {

    @Test
    public void resolveAndReply() {

        StrategiesRegistry registry = new StrategiesRegistry();
        registry.add(new GreatingReplyStrategy());
        registry.add(new HowAreYouReplyStrategy());
        registry.add(new LifeMeaningReplyStrategy());
        registry.add(new LazyReplyStrategy());

        String actual = registry.resolveAndReply("В чем смысл жизни?");
        assertTrue(actual != null && actual.length() > 0);
    }
}