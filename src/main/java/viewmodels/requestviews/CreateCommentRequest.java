package viewmodels.requestviews;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class CreateCommentRequest {
    String text,userEmail;
    int postId;

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(String text, String userEmail, int postId) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
