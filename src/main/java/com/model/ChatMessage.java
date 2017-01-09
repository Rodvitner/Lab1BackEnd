package com.model;

import com.google.appengine.api.datastore.Key;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by simonlundstrom on 24/11/16.
 */
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;

    private String text;
    private Date date;

    @ManyToOne
    private Chatroom room;


    private String poster;

    public ChatMessage() {
    }

    public ChatMessage(String text, Date date, Chatroom room, String poster) {
        this.text = text;
        this.date = date;
        this.room = room;
        this.poster = poster;
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", poster=" + poster +
                '}';
    }
}
