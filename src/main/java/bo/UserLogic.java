package bo;

import exception.UserException;
import model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 * Created by archer on 2016-11-21.
 */

/**
 * class for performing logic and peristing to db. will handle transactions
 * all calls made to this class will only stage changes for db.
 */
public class UserLogic {
    EntityManager manager;

    public UserLogic(EntityManager manager) {
        this.manager = manager;
    }


    private void createFriendRel(User user1, User user2) {
        EntityTransaction trans = null;
        try {
            trans =manager.getTransaction();
            trans.begin();

            user1.getFriends().add(user2);
            user2.getFriends().add(user1);
            manager.persist(user1);
            manager.persist(user2);

            trans.commit();
        } catch (PersistenceException ex) {
            trans.rollback();
            ex.printStackTrace();
            throw ex;
        }


    }

    public User getUser(String email) {
        return manager.find(User.class, email);
    }


    public void makeFriends(String user1Email, String user2Email) throws UserException {


        User user1 = getUser(user1Email);
        //user not found
        if (user1 == null) {
            throw new UserException("no such user");
        }

        //check if already friends
        for (User u :
                user1.getFriends()) {
            if (u.getEmail().equals(user2Email)) {
                throw new UserException("users are already friends");
            }

        }

        //check if second user exists
        User user2 = getUser(user2Email);
        if (user2 == null){
            throw new UserException("second user does not exist");
        };

        //create relationship
        createFriendRel(user1, user2);


    }


}
