package viewmodels.resultviews;

/**
 * Created by simonlundstrom on 19/11/16.
 */
public class LoginResult extends Result {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LoginResult(boolean success, String message, String uuid) {
        super(success, message);
        this.uuid = uuid;
    }
}
