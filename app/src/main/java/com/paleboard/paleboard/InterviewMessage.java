package com.paleboard.paleboard;

import java.util.Date;

public class InterviewMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;

    public InterviewMessage(String text, String user) {
        this.messageText = text;
        this.messageUser = user;
        messageTime = new Date().getTime();
    }

    public InterviewMessage() {}

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String text) {
        this.messageText = text;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String user) {
        this.messageUser = user;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long time) {
        this.messageTime = time;
    }
}
