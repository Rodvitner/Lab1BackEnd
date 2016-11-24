package bo;

import exception.UserException;
import model.User;
import viewmodels.requestviews.CreateUserRequest;
import viewmodels.resultviews.CreateUserResult;
import viewmodels.resultviews.GetUserResult;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by archer on 2016-11-21.
 */

/**
 * class for performing logic and persisting to db. Will handle transactions
 */
class UserLogic {
    private EntityManager manager;

    UserLogic(EntityManager manager) {
        this.manager = manager;
    }

    // Paketgenerisk metod f|r att hitta en anv{ndare i databasen (efter epostadress).
    User findUserByEmail(String email) throws UserException{
        if (email==null) throw new NullPointerException("User email is null.");
        System.out.println("User logic trying to find user: "+email);
        User res = manager.find(User.class, email);
        System.out.println("User logic found "+res);
        return res;
    }

    // Paketpublik wrapper-metod f|r att logga in en anv{ndare.
    String loginUser(String email, String password) throws UserException {
        User userToLogin = findUserByEmail(email);
        if (userToLogin == null) {
            throw new UserException("No such user.");
        }
        if (!userToLogin.getPassword().equals(password)) {
            throw new UserException("Wrong password.");
        }
        return loginUser(userToLogin);
    }

    // Privat metod f|r att logga in en anv{ndare.
    // Returnerar den nya uuidn.
    private String loginUser(User user) {
        // String newUuid = createRandomUuid();
        String newUuid = UUID.randomUUID().toString();
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            user.setUuid(newUuid);
            manager.persist(user);
            trans.commit();
        } catch (PersistenceException pex) {
            if (trans != null) trans.rollback();
            pex.printStackTrace();
            throw pex;
        }
        return newUuid;
    }

    // Paketpublik wrapper-metod f|r att skapa en v{nrelation. Kollar om anv{ndarna finns.
    void makeFriends(String user1Email, String user2Email) throws UserException {

        User user1 = findUserByEmail(user1Email);

        if (user1 == null) {
            throw new UserException("First user not found.");
        }

        //check if already friends
        for (User u :
                user1.getFriends()) {
            if (u.getEmail().equals(user2Email)) {
                throw new UserException("users are already friends");
            }
        }

        //check if second user exists
        User user2 = findUserByEmail(user2Email);
        if (user2 == null) {
            throw new UserException("second user does not exist");
        }

        //create relationship
        createFriendRel(user1, user2);
    }

    // Den privata metoden f|r att skapa en v{nrelation. Lagrar i databasen.
    private void createFriendRel(User user1, User user2) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            user1.getFriends().add(user2);
            user2.getFriends().add(user1);
            manager.persist(user1);
            manager.persist(user2);
            trans.commit();
        } catch (PersistenceException ex) {
            if (trans != null) trans.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }

    // Paketpublik wrapper-metod f|r att registrera en anv{ndare.
    // Kollar om epostadressen finns i databasen.
    void registerUser(String name, String email, String password) throws UserException {
        User user = findUserByEmail(email);

        //email of that user found.
        if (user != null) {
            throw new UserException("Email already in use.");
        }
        createUser(new User(name, email, password));
    }

    // Den privata metoden f|r att registrera en ny anv{ndare. Lagrar i DB.
    private void createUser(User newUser) {
        EntityTransaction transaction = null;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(newUser);
            transaction.commit();
        } catch (PersistenceException ex) {
            if (transaction != null) transaction.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<User> listUsersbyName(String name, int startAt, int amountOf) {
        Query q = manager.createQuery("SELECT u FROM User u WHERE u.name like :name")
                .setFirstResult(startAt)
                .setMaxResults(amountOf);
        q.setParameter("name",name);
        return (List<User>)q.getResultList();
    }

    public void logoutUser(String userToLogout) throws UserException{
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            User u = findUserByEmail(userToLogout);
            if (u==null) throw new UserException("User not found.");
            trans.begin();
            u.setUuid(null);
            manager.persist(u);
            trans.commit();
        }catch (PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
        }
    }

    public void removeRelation(String angryGuy, String theEnemy) throws UserException{
        User angry = findUserByEmail(angryGuy);
        User enemy = findUserByEmail(theEnemy);
        if (angry==null) throw new UserException("User "+angryGuy+" not found.");
        if (enemy==null) throw new UserException("User "+theEnemy+" not found.");
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            angry.getFriends().remove(enemy);
            enemy.getFriends().remove(angry);
            manager.persist(angry);
            manager.persist(enemy);
            trans.commit();
        }catch(PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
            throw pe;
        }
    }
}
