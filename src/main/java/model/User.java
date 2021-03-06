package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Entity
public class User {
    private String name;

    @Id
    private String email;

    private String password;

    private String uuid;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "poster")
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    //have a user able to be friends with other users
    @ManyToMany
    @JoinTable(joinColumns =  @JoinColumn(name="user1"),
                inverseJoinColumns = @JoinColumn(name ="user2"),
                uniqueConstraints =  @UniqueConstraint(columnNames = {"user1","user2"}))
    private List<User> friends;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="member"),
            inverseJoinColumns = @JoinColumn(name="chatroom"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"chatroom","member"}))
    private List<Chatroom> rooms;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<Chatroom> getRooms() {
        return rooms;
    }

    public void setRooms(List<Chatroom> rooms) {
        this.rooms = rooms;
    }

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
