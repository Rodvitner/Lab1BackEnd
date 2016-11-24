package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Entity
public class Post {
    private String text;

    private Date date;

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post() {
    }

    public Post(String text, Date date, User user) {
        this.text = text;
        this.date = date;
        this.user = user;
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
}
