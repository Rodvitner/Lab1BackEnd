package viewmodels.requestviews;

import java.util.Date;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class WallRequest {
    private String userEmail;
    private int startAt, amountOfPosts;

    public WallRequest() {
    }

    public WallRequest(String userEmail, int startAt, int amountOfPosts) {
        this.userEmail = userEmail;
        this.startAt = startAt;
        this.amountOfPosts = amountOfPosts;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getAmountOfPosts() {
        return amountOfPosts;
    }

    public void setAmountOfPosts(int amountOfPosts) {
        this.amountOfPosts = amountOfPosts;
    }
}
