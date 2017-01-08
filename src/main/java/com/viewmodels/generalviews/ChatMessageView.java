package com.viewmodels.generalviews;

import com.google.appengine.api.datastore.Key;

import java.util.Date;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatMessageView {
    String text,posterId;
    Date date;
    Key roomId, chatMessageId;

    public ChatMessageView() {
    }

    public ChatMessageView(String text, String posterId, Date date, Key roomId, Key chatMessageId) {
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

    public Key getRoomId() {
        return roomId;
    }

    public void setRoomId(Key roomId) {
        this.roomId = roomId;
    }

    public Key getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(Key chatMessageId) {
        this.chatMessageId = chatMessageId;
    }
}
