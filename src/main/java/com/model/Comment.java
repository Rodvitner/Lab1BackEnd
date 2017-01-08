package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @GeneratedValue
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
