package com.viewmodels.generalviews;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class PostView {
    String text, date, userId;
    int postId;

    public PostView() {
    }

    public PostView(String text, String date, String userId, int postId) {
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
