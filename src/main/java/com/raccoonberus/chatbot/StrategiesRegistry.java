package com.raccoonberus.chatbot;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class StrategiesRegistry {

    private Set<ReplyStrategy> strategies = new LinkedHashSet<>();

    public void add(String className) {
        try {
            strategies.add((ReplyStrategy) Class.forName(className).newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add(ReplyStrategy strategy) {
        strategies.add(strategy);
    }

    public void delete(String className) {
        //
    }

    public String resolveAndReply(String message) {
        for (ReplyStrategy strategy : strategies) {
            if (strategy.isSupport(message)) {
                return strategy.run(message);
            }
        }
        return null;
    }

}
