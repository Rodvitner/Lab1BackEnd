package viewmodels.resultviews;

/**
 * Created by archer on 2016-11-20.
 */
public class CreateUserResult {

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

    public CreateUserResult(boolean ASuccess, String status) {
        this.success = ASuccess;
        this.reason = status;
    }

    public CreateUserResult() {
    }
}
