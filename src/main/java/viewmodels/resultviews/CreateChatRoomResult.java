package viewmodels.resultviews;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CreateChatRoomResult extends Result {
    int id;

    public CreateChatRoomResult(boolean success, String message, int id) {
        super(success, message);
        this.id = id;
    }

    public CreateChatRoomResult() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
