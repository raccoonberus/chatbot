package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageResponse {

    @JsonProperty
    private boolean ok = false;

    @JsonProperty
    private MessageItem result;

    public boolean isOk() {
        return ok;
    }

    public SendMessageResponse setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public MessageItem getResult() {
        return result;
    }

    public SendMessageResponse setResult(MessageItem result) {
        this.result = result;
        return this;
    }
}
