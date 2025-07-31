package by.it_academy.jd2.Mk_JD2_111_25.HW4.core.dto;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime sendingTime;
    private String sender;
    private String recipient;
    private String text;

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
