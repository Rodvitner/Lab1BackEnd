package viewmodels.resultviews;

import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class FriendListResult extends Result{
    List<GetUserResult> friends;

    public List<GetUserResult> getFriends() {
        return friends;
    }

    public void setFriends(List<GetUserResult> friends) {
        this.friends = friends;
    }

    public FriendListResult(boolean success, String message, List<GetUserResult> friends) {
        super(success, message);
        this.friends = friends;
    }

    public FriendListResult() {
    }
}
