package com.viewmodels.generalviews;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class CommentView {
    private String text, tid, userId;
    private int id,postId;

    public CommentView() {
    }

    public CommentView(String text, String tid, int id, int postId, String userId) {
        this.text = text;
        this.tid = tid;
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
