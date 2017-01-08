package com.model;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.Persistent;
import javax.persistence.*;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
@Entity
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;

    @OneToMany(mappedBy = "room")
    private List<ChatMessage> messages;

    @Persistent
    private List<Key> memberIds;

    private String name;

    public Chatroom(List<Key> memberIds, String roomName) {
        this.memberIds = memberIds;
        this.name = roomName;
    }

    public Chatroom() {
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public List<Key> getMemberIds() {
        return memberIds;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setMemberIds(List<Key> members) {
        this.memberIds = members;
    }

    public Key getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId( Key id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", messages=" + messages +
                '}';
    }
}
