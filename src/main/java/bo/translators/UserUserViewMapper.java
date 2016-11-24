package bo.translators;

import model.User;
import viewmodels.generalviews.UserView;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class UserUserViewMapper extends Translator<User,UserView>{
    @Override
    public UserView translateFromA(User user) {
        return new UserView(user.getEmail(),user.getName());
    }

    @Override
    public User translateFromB(UserView userView) {
        return null;
    }
}
