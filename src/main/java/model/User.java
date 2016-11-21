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

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Commentary> comments;

    //have a user able to be friends with other users
    @ManyToMany
    @JoinTable(joinColumns =  @JoinColumn(name="user1"),
                inverseJoinColumns = @JoinColumn(name ="user2"),
                uniqueConstraints =  @UniqueConstraint(columnNames = {"user1","user2"}))
    private List<User> friends;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Commentary> getComments() {
        return comments;
    }

    public void setComments(List<Commentary> comments) {
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

    public User() {
    }
}
