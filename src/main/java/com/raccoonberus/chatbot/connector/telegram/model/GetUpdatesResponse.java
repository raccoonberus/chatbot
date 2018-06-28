package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUpdatesResponse
{
    public class ResultItem {

        @JsonProperty("update_id")
        private long updateId;

        public long getUpdateId() {
            return updateId;
        }

        public ResultItem setUpdateId(long updateId) {
            this.updateId = updateId;
            return this;
        }
    }

    @JsonProperty
    private boolean ok = false;

    @JsonProperty
    private ResultItem[] result;

    public boolean isOk() {
        return ok;
    }

    public GetUpdatesResponse setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public ResultItem[] getResult() {
        return result;
    }

    public GetUpdatesResponse setResult(ResultItem[] result) {
        this.result = result;
        return this;
    }
}
