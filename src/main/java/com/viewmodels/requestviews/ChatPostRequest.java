package com.viewmodels.requestviews;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatPostRequest {
    String posterId,text;
    String chatroomId;

    public ChatPostRequest() {
    }

    public ChatPostRequest(String posterId, String text, String chatroomId) {
        this.posterId = posterId;
        this.text = text;
        this.chatroomId = chatroomId;
    }

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }
}
