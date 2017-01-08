package com.model;

import com.google.appengine.api.datastore.Key;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Entity
public class Comment {
    private String text;
    private Date date;
    private String username;
    @ManyToOne
    private Post post;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;

    public Comment() {
    }

    public Comment(String text, Date date, String username, Post post) {
        this.text = text;
        this.date = date;
        this.username = username;
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
