package viewmodels;

/**
 * Created by archer on 2016-11-21.
 */
public class BefriendUserResult {
   private boolean success;
   private String reason;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
