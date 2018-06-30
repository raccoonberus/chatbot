package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

//@JsonSerialize
public class FromItem implements Serializable {
    @JsonProperty
//    @JsonIgnore
    private Long id;

    @JsonProperty("is_bot")
//    @JsonIgnore
    private boolean isBot;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("language_code")
    private String languageCode;

    public FromItem() {
    }

    public Long getId() {
        return id;
    }

    public FromItem setId(Long identifier) {
        this.id = identifier;
        return this;
    }

    public boolean isBot() {
        return isBot;
    }

    public FromItem setIsBot(boolean isBot) {
        this.isBot = isBot;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public FromItem setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public FromItem setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public FromItem setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public FromItem setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }
}
