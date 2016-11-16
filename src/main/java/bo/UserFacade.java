package bo;

import model.User;
import viewmodels.UserView;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class UserFacade {
    EntityManager prylchef;

    public UserFacade(EntityManager em) {
        prylchef = em;
    }

    public UserView saveUser(UserView incoming) {
        EntityTransaction transa = prylchef.getTransaction();
        transa.begin();
        User toBeSaved = new User();
        toBeSaved.setEmail(incoming.getEmail());
        toBeSaved.setName(incoming.getName());
        toBeSaved.setPassword(incoming.getPassword());
        prylchef.persist(toBeSaved);
        transa.commit();
        prylchef.close();
        return new UserView(toBeSaved.getName(),toBeSaved.getEmail(),null);
    }
}
