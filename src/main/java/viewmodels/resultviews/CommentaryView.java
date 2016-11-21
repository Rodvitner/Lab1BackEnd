package viewmodels.resultviews;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class CommentaryView {
    private String text, tid;
    private int id,postId,userId;

    public CommentaryView() {
    }

    public CommentaryView(String text, String tid, int id, int postId, int userId) {
        this.text = text;
        this.tid = tid;
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
