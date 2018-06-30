package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUpdatesResponse {

    @JsonProperty
    private boolean ok = false;

    @JsonProperty
    private List<ResultItem> result = new ArrayList<>();

    public boolean isOk() {
        return ok;
    }

    public GetUpdatesResponse setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public List<ResultItem> getResult() {
        return result;
    }

    public GetUpdatesResponse setResult(List<ResultItem> result) {
        this.result = result;
        return this;
    }

}
