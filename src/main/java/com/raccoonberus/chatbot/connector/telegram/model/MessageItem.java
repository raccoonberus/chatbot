package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raccoonberus.chatbot.connector.ChatMessage;

import java.util.Date;
import java.util.List;

/**
 * @link https://core.telegram.org/bots/api#message
 */
public class MessageItem implements ChatMessage {
    @JsonProperty("message_id")
    private int messageId;

    @JsonProperty("from")
    private FromItem from;

    @JsonProperty("date")
    private long date;

    @JsonProperty
    private ChatItem chat;

    // forward_from
    // forward_from_chat
    // forward_from_message_id
    // forward_signature
    // forward_date
    // reply_to_message
    // edit_date
    // media_group_id
    // author_signature

    @JsonProperty("text")
    private String text;

    @JsonProperty("entities")
    @JsonIgnore()
    private List<MessageEntity> entities;

    @JsonProperty("caption_entities")
    @JsonIgnore()
    private List<MessageEntity> captionEntities;

    @JsonProperty("sticker")
    @JsonIgnore()
    private Object sticker;

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

    @Override
    public Date sentAt() {
        return new Date(date * 1000);
    }

    @Override
    public String getSenderId() {
        return chat != null
                ? chat.getId().toString()
                : from.getId().toString();
    }

    @Override
    public String getMessageText() {
        return text;
    }
}
