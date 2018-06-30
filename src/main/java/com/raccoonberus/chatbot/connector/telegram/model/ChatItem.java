package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatItem {
    @JsonProperty("id")
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("type")
    private String type;

    public ChatItem() {
    }

    public long getId() {
        return id;
    }

    public ChatItem setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ChatItem setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ChatItem setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ChatItem setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getType() {
        return type;
    }

    public ChatItem setType(String type) {
        this.type = type;
        return this;
    }
}
