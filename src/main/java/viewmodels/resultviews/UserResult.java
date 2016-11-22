package viewmodels.resultviews;

/**
 * Created by archer on 2016-11-22.
 */
public class UserResult {
    String email;
    String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserResult() {
    }

    public UserResult(String email, String name) {

        this.email = email;
        this.name = name;
    }
}
