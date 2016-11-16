package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Entity
public class Commentary {
    private String text;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @Id
    @GeneratedValue
    private int id;

    public Commentary() {
    }

    public Commentary(String text, Date date, User user, Post post) {
        this.text = text;
        this.date = date;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
