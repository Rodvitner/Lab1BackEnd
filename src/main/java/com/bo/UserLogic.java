package com.bo;

import com.exception.UserException;
import com.google.appengine.api.datastore.Key;
import com.model.User;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    User findUserByEmail(String email) throws UserException {
        if (email == null) throw new NullPointerException("User email is null.");
        System.out.println("User logic trying to find user: " + email);
        Query q = manager.createQuery("select u from User u where u.email = :email ", User.class);
        User res;
        try{

            List<User> users = (List<User>) q.setParameter("email", email).getResultList();
            if(users.size() == 0){
                res =null;
            }else{
                res=users.get(0);
            }

        }catch (NoResultException e){
            res = null;
        }
        System.out.println("User logic found " + res);
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
                getFriends(user1)) {
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
            user1.getFriendsIds().add(user2.getId());
            user2.getFriendsIds().add(user1.getId());
            manager.persist(user1);
            manager.persist(user2);
            trans.commit();
        } catch (PersistenceException ex) {
            if (trans != null) trans.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<User> getFriends(User asker) {
        List<User> res = new ArrayList<>();
        System.out.println("ASKER NULL" + (asker == null) + "ASKER friendids NULL" + (asker.getFriendsIds() == null) );

        for(Key k : asker.getFriendsIds()){

            Query q = manager.createQuery("select u from User u where u.id = :id", User.class);
            q.setParameter("id",k);
           res.add((User)q.getSingleResult());
        }
        System.out.println("FOUDN THIS LIST OF FRIENDS: " +res ) ;
       return res;

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
        Query q = manager.createQuery("SELECT u FROM User u WHERE u.email like :name")
                .setFirstResult(startAt)
                .setMaxResults(amountOf);
        System.out.println("TRYEING TO FIND USRESR WITH NAME LIKE " + name );
        q.setParameter("name",  name + "%");
        return (List<User>) q.getResultList();
    }

    public void logoutUser(String userToLogout) throws UserException {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            User u = findUserByEmail(userToLogout);
            if (u == null) throw new UserException("User not found.");
            trans.begin();
            u.setUuid(null);
            manager.persist(u);
            trans.commit();
        } catch (PersistenceException pe) {
            if (trans != null) trans.rollback();
            pe.printStackTrace();
        }
    }

    public void removeRelation(String angryGuy, String theEnemy) throws UserException {
        User angry = findUserByEmail(angryGuy);
        User enemy = findUserByEmail(theEnemy);
        if (angry == null) throw new UserException("User " + angryGuy + " not found.");
        if (enemy == null) throw new UserException("User " + theEnemy + " not found.");
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            angry.getFriendsIds().remove(enemy.getId());
            enemy.getFriendsIds().remove(angry.getId());
            manager.persist(angry);
            manager.persist(enemy);
            trans.commit();
        } catch (PersistenceException pe) {
            if (trans != null) trans.rollback();
            pe.printStackTrace();
            throw pe;
        }
    }
}
