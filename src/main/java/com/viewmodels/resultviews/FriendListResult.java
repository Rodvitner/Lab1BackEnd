package com.viewmodels.resultviews;

import com.viewmodels.generalviews.UserView;

import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class FriendListResult extends Result{
    List<UserView> friends;

    public List<UserView> getFriends() {
        return friends;
    }

    public void setFriends(List<UserView> friends) {
        this.friends = friends;
    }

    public FriendListResult(boolean success, String message, List<UserView> friends) {
        super(success, message);
        this.friends = friends;
    }

    public FriendListResult() {
    }
}
