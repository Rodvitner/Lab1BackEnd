package com.viewmodels.generalviews;

import java.util.Date;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatMessageView {
    String text,posterId;
    Date date;
    int roomId, chatMessageId;

    public ChatMessageView() {
    }

    public ChatMessageView(String text, String posterId, Date date, int roomId, int chatMessageId) {
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(int chatMessageId) {
        this.chatMessageId = chatMessageId;
    }
}
