package com.viewmodels.generalviews;


/**
 * Created by simonlundstrom on 16/11/16.
 */
public class PostView {
    String text, date, userId;
    String postId;

    public PostView() {
    }

    public PostView(String text, String date, String userId, String postId) {
        this.text = text;
        this.date = date;
        this.userId = userId;
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
