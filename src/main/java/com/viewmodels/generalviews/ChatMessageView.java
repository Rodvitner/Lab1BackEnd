package com.viewmodels.generalviews;


import java.util.Date;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatMessageView {
    String text,posterId;
    Date date;
    String roomId, chatMessageId;

    public ChatMessageView() {
    }

    public ChatMessageView(String text, String posterId, Date date, String roomId, String chatMessageId) {
        this.text = text;
        this.posterId = posterId;
        this.date = date;
        this.roomId = roomId;
        this.chatMessageId = chatMessageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(String chatMessageId) {
        this.chatMessageId = chatMessageId;
    }
}
