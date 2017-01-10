package com.viewmodels.generalviews;

import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatRoomView {
    private String name;
    private String id;
    private List<UserView> members;
    private List<ChatMessageView> messages;

    public ChatRoomView(String name, String id, List<UserView> members, List<ChatMessageView> messages) {
        this.name = name;
        this.id = id;
        this.members = members;
        this.messages = messages;
    }

    public ChatRoomView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UserView> getMembers() {
        return members;
    }

    public void setMembers(List<UserView> members) {
        this.members = members;
    }

    public List<ChatMessageView> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageView> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatRoomView{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", members=" + members +
                ", messages=" + messages +
                '}';
    }
}
