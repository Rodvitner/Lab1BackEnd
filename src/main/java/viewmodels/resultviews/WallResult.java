package viewmodels.resultviews;

import viewmodels.generalviews.PostView;

import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class WallResult extends Result {
    List<PostView> postViews;

    public WallResult(boolean success, String message, List<PostView> postViews) {
        super(success, message);
        this.postViews = postViews;
    }

    public List<PostView> getPostViews() {
        return postViews;
    }

    public void setPostViews(List<PostView> postViews) {
        this.postViews = postViews;
    }
}
