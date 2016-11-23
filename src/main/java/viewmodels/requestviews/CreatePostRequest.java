package viewmodels.requestviews;

import java.util.Date;

/**
 * Created by archer on 2016-11-20.
 */
public class CreatePostRequest {
    String postText, userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

}
