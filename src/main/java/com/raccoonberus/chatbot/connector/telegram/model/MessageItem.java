package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageItem {
    @JsonProperty("message_id")
    private int messageId;

    @JsonProperty("from")
    private FromItem from;

    @JsonProperty
    private ChatItem chat;

    @JsonProperty("date")
    private long date;

    @JsonProperty("text")
    private String text;

    public MessageItem() {
    }

    public int getMessageId() {
        return messageId;
    }

    public MessageItem setMessageId(int messageId) {
        this.messageId = messageId;
        return this;
    }

    public FromItem getFrom() {
        return from;
    }

    public MessageItem setFrom(FromItem from) {
        this.from = from;
        return this;
    }

    public ChatItem getChat() {
        return chat;
    }

    public MessageItem setChat(ChatItem chat) {
        this.chat = chat;
        return this;
    }

    public long getDate() {
        return date;
    }

    public MessageItem setDate(long date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public MessageItem setText(String text) {
        this.text = text;
        return this;
    }
}
