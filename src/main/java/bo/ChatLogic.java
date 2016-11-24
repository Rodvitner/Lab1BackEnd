package bo;

import exception.UserException;
import model.Chatroom;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatLogic {
    EntityManager manager;

    public ChatLogic(EntityManager manager) {
        this.manager = manager;
    }

    public int createChatroom(User creator, User invitee) throws UserException {
        if (creator==null) throw new UserException("Creator is null when creating chatroom.");
        if (invitee==null) throw new UserException("Invitee is null when creating chatroom.");
        List<User> members = new LinkedList<>();
        members.add(creator);
        members.add(invitee);
        return createChatroom(new Chatroom(members));
    }
    private int createChatroom(Chatroom newRoom) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(newRoom);
            trans.commit();
        }catch(PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
        }
        return newRoom.getId();
    }
}
