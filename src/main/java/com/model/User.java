package com.model;

import com.google.appengine.api.datastore.Key;
import com.viewmodels.requestviews.WallRequest;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Entity
public class User {
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;

    private String email;

    private String password;

    private String uuid;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;



    //have a user able to be friends with other users
    @Persistent
    private List<Key> friendsIds;
    //private List<User> friends;
    @Persistent
    private List<Key> roomIds;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

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



    public List<Key> getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(List<Key> friends) {
        this.friendsIds = friends;
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


    public List<Key> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<Key> rooms) {
        this.roomIds = rooms;
    }

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.friendsIds = new ArrayList<>();
        this.roomIds = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", posts=" + posts +
                ", friendsIds=" + friendsIds +
                ", roomIds=" + roomIds +
                '}';
    }
}
