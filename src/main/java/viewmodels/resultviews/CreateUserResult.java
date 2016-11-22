package viewmodels.resultviews;

/**
 * Created by archer on 2016-11-20.
 */
public class CreateUserResult {

    private boolean ASuccess;
    private String status;

    public boolean isASuccess() {
        return ASuccess;
    }

    public void setASuccess(boolean ASuccess) {
        this.ASuccess = ASuccess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CreateUserResult(boolean ASuccess, String status) {
        this.ASuccess = ASuccess;
        this.status = status;
    }

    public CreateUserResult() {
    }
}
