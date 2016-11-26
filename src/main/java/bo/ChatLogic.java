package bo;

import exception.ChatException;
import exception.UserException;
import model.ChatMessage;
import model.Chatroom;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatLogic {
    private EntityManager manager;

    ChatLogic(EntityManager manager) {
        this.manager = manager;
    }

    int createChatroom(User creator, User invitee, String roomName) throws UserException {
        if (creator==null) throw new UserException("Creator is null when creating chatroom.");
        if (invitee==null) throw new UserException("Invitee is null when creating chatroom.");
        if (!creator.getFriends().contains(invitee)) throw new UserException("Users are not friends!");
        List<User> members = new LinkedList<>();
        members.add(creator);
        members.add(invitee);
        return createChatroom(new Chatroom(members, roomName));
    }
    private int createChatroom(Chatroom newRoom) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            for(User u : newRoom.getMembers()) {
                System.out.println(u);
                u.getRooms().add(newRoom);
            }
            manager.persist(newRoom);
            trans.commit();
        }catch(PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
        }
        return newRoom.getId();
    }

    Chatroom getChatRoomById(int id) {
        return manager.find(Chatroom.class,id);
    }

    // Paketpublik metod f|r att posta till en chatt. Kastar lite exceptions.
    void postChatMessage(int chatroomId, User poster, String text) throws ChatException, UserException {
        if (poster==null) throw new UserException("User null when trying to post to chatroom.");
        Chatroom roomToPostIn = getChatRoomById(chatroomId);
        if (roomToPostIn==null) throw new ChatException("Chatroom not found");
        if (!roomToPostIn.getMembers().contains(poster)) throw new ChatException("User not member of chat!");
        postChatMessage(new ChatMessage(text,new Date(),roomToPostIn,poster));
    }

    // Privat metod f|r att posta till en chatt. Kontaktar databasen.
    private void postChatMessage(ChatMessage message) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(message);
            trans.commit();
        }catch(PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
        }
    }

    void addUserToChatroom(int chatRoomId,User userToAdd) throws UserException,ChatException{
        if (userToAdd==null) throw new UserException("User null when adding to chatroom.");
        Chatroom room = getChatRoomById(chatRoomId);
        if (room==null) throw new ChatException("Chatroom not found when adding user to chatroom.");
        addUserToChatroom(room, userToAdd);
    }

    private void addUserToChatroom(Chatroom room, User user) {
        EntityTransaction trans=null;
        try {
            trans=manager.getTransaction();
            trans.begin();
            room.getMembers().add(user);
            user.getRooms().add(room);
            manager.persist(room);
            manager.persist(user);
            trans.commit();
        }catch(PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
        }
    }
}
