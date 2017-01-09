package com.viewmodels.generalviews;


/**
 * Created by simonlundstrom on 16/11/16.
 */
public class CommentView {
    private String text, tid, userId;
    private String id,postId;

    public CommentView() {
    }

    public CommentView(String text, String tid, String id, String postId, String userId) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
