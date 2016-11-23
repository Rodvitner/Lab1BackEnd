package viewmodels.resultviews;

/**
 * Created by archer on 2016-11-20.
 */
public class CreatePostResult extends Result {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreatePostResult(boolean success, String message, int id) {
        super(success, message);
        this.id = id;
    }

    public CreatePostResult(boolean success, String message) {
        super(success, message);
    }

    public CreatePostResult() {
    }
}
