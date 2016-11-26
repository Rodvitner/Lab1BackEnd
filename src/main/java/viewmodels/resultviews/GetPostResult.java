package viewmodels.resultviews;

import viewmodels.generalviews.PostView;

/**
 * Created by simonlundstrom on 26/11/16.
 */
public class GetPostResult extends Result {
    PostView post;

    public GetPostResult(boolean success, String message, PostView post) {
        super(success, message);
        this.post = post;
    }

    public GetPostResult() {
    }

    public PostView getPost() {
        return post;
    }

    public void setPost(PostView post) {
        this.post = post;
    }
}
