package com.raccoonberus.chatbot.connector.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUpdatesResponse {

    public class ResultItem {

        public class MessageItem {

            public class FromItem {

                @JsonProperty("id")
                private long id;

                @JsonProperty("is_bot")
                private boolean isBot;

                @JsonProperty("first_name")
                private String firstName;

                @JsonProperty("username")
                private String username;

                @JsonProperty("language_code")
                private String languageCode;

                public long getId() {
                    return id;
                }
                public FromItem setId(long id) {
                    this.id = id;
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

            public class ChatItem {

                @JsonProperty("id")
                private long id;

                @JsonProperty("first_name")
                private String firstName;

                @JsonProperty("username")
                private String username;

                @JsonProperty("type")
                private String type;

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

                public String getUsername() {
                    return username;
                }
                public ChatItem setUsername(String username) {
                    this.username = username;
                    return this;
                }

                public String type() {
                    return type;
                }
                public ChatItem setType(String type) {
                    this.type = type;
                    return this;
                }
            }

            @JsonProperty("message_id")
            private long messageId;

            @JsonProperty
            private FromItem from;

            @JsonProperty
            private ChatItem chat;

            @JsonProperty("date")
            private long date;

            @JsonProperty("text")
            private String text;

            public long getMessageId() {
                return messageId;
            }
            public MessageItem setMessageId(long messageId) {
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
            public MessageItem setFrom(ChatItem chat) {
                this.chat = chat;
                return this;
            }

            public long getDate() {
                return date;
            }
            public MessageItem setFrom(long date) {
                this.date = date;
                return this;
            }

            public String getText() {
                return text;
            }
            public MessageItem setFrom(String text) {
                this.text = text;
                return this;
            }
        }

        @JsonProperty("update_id")
        private long updateId;

        @JsonProperty
        private MessageItem message;

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
