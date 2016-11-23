package bo.translators;

import model.User;
import viewmodels.resultviews.GetUserResult;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class UserUserViewMapper extends Translator<User,GetUserResult>{
    @Override
    public GetUserResult translateFromA(User user) {
        return new GetUserResult(user.getEmail(),user.getName());
    }

    @Override
    public User translateFromB(GetUserResult getUserResult) {
        return null;
    }
}
