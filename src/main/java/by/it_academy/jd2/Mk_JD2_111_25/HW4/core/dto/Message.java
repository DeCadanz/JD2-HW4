package by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto;

import java.time.LocalDateTime;

public class Message {
    private final LocalDateTime sendingTime;
    private final String sender;
    private final String recipient;
    private final String text;

    private Message(LocalDateTime sendingTime, String sender, String recipient, String text) {
        this.sendingTime = sendingTime;
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }

    public static Message.Builder builder() {
        return new Message.Builder();
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    public static class Builder {
        private LocalDateTime sendingTime;
        private String sender;
        private String recipient;
        private String text;

        public Builder sendingTime(LocalDateTime sendingTime) {
            this.sendingTime = sendingTime;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(sendingTime, sender, recipient, text);
        }
    }
}
