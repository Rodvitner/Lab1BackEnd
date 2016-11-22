package bo;

import exception.UserException;
import model.User;
import viewmodels.requestviews.CreateUserRequest;
import viewmodels.resultviews.CreateUserResult;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

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
    User findUserByEmail(String email) {
        return manager.find(User.class, email);
    }

    // Generisk metod f|r att generera ett random uuid p} 12 bokst{ver eller n}t.
    private String createRandomUuid() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<12; i++) sb.append((char)((int)(Math.random()*92)+33));
        return sb.toString();
    }

    // Paketpublik wrapper-metod f|r att logga in en anv{ndare.
    String loginUser(String email, String password) throws UserException{
        User userToLogin = findUserByEmail(email);
        if (userToLogin==null) {
            throw new UserException("No such user.");
        }
        if (!userToLogin.getPassword().equals(password)) {
            throw new UserException("Wrong password.");
        }
        return loginUser(userToLogin);
    }

    // Privat metod f|r att logga in en anv{ndare.
    // Returnerar den nya uuidn.
    private String loginUser(User user){
        String newUuid = createRandomUuid();
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            user.setUuid(newUuid);
            manager.persist(user);
            trans.commit();
        }
        catch(PersistenceException pex) {
            if (trans!=null) trans.rollback();
            pex.printStackTrace();
            throw pex;
        }
        return newUuid;
    }

    // Paketpublik wrapper-metod f|r att skapa en v{nrelation. Kollar om anv{ndarna finns.
    void makeFriends(String user1Email, String user2Email) throws UserException {

        User user1 = findUserByEmail(user1Email);

        if (user1==null) {
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
            if (trans!=null) trans.rollback();
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
        createUser(new User(name,email,password));
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
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
}
