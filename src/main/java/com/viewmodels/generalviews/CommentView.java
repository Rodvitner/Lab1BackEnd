package com.viewmodels.generalviews;

import com.google.appengine.api.datastore.Key;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class CommentView {
    private String text, tid, userId;
    private Key id,postId;

    public CommentView() {
    }

    public CommentView(String text, String tid, Key id, Key postId, String userId) {
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

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public Key getPostId() {
        return postId;
    }

    public void setPostId(Key postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
