package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultItem {
    @JsonProperty("update_id")
    private long updateId;

    @JsonProperty
    private MessageItem message;

    public ResultItem() {
    }

    public long getUpdateId() {
        return updateId;
    }

    public ResultItem setUpdateId(long updateId) {
        this.updateId = updateId;
        return this;
    }

    public MessageItem getMessage() {
        return message;
    }

    public ResultItem setMessage(MessageItem message) {
        this.message = message;
        return this;
    }
}
