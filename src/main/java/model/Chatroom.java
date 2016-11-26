package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
@Entity
public class Chatroom {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "room")
    private List<ChatMessage> messages;

    @ManyToMany(mappedBy = "rooms")
    private List<User> members;

    private String name;

    public Chatroom(List<User> members, String roomName) {
        this.members = members;
        this.name = roomName;
    }

    public Chatroom() {
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
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
