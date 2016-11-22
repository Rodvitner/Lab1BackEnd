package viewmodels.resultviews;

/**
 * Created by simonlundstrom on 19/11/16.
 */
public class LoginResult {
    private boolean login;
    private String reason,uuid;

    public LoginResult() {
    }

    public LoginResult(boolean login, String reason) {
        this.login = login;
        this.reason = reason;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
