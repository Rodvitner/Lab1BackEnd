package viewmodels;

/**
 * Created by simonlundstrom on 19/11/16.
 */
public class LoginStatusReturnMessage {
    private boolean login;
    private String reason;

    public LoginStatusReturnMessage() {
    }

    public LoginStatusReturnMessage(boolean login, String reason) {
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
}
