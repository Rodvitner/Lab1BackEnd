package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue
    private int id;

    private String text;
    private Date date;

    @ManyToOne
    private Chatroom room;

    @ManyToOne
    private User poster;

    public ChatMessage() {
    }

    public ChatMessage(String text, Date date, Chatroom room, User poster) {
        this.text = text;
        this.date = date;
        this.room = room;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }
}
