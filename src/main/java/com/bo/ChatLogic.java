package com.bo;

import com.bo.translators.ChatMessageSorter;
import com.exception.ChatException;
import com.exception.UserException;
import com.google.appengine.api.datastore.Key;
import com.model.ChatMessage;
import com.model.Chatroom;
import com.model.User;
import com.viewmodels.resultviews.GetChatroomResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
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

    Key createChatroom(User creator, User invitee, String roomName) throws UserException {
        if (creator == null) throw new UserException("Creator is null when creating chatroom.");
        if (invitee == null) throw new UserException("Invitee is null when creating chatroom.");
        if (!creator.getFriendsIds().contains(invitee)) throw new UserException("Users are not friends!");
        List<Key> members = new LinkedList<>();
        members.add(creator.getId());
        members.add(invitee.getId());
        return createChatroom(new Chatroom(members, roomName));
    }

    private Key createChatroom(Chatroom newRoom) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(newRoom);

            for (User u : getUsersInroom(newRoom)) {
                System.out.println(u);
                u.getRoomIds().add(newRoom.getId());
            }
            trans.commit();
        } catch (PersistenceException pe) {
            if (trans != null) trans.rollback();
            pe.printStackTrace();
        }
        return newRoom.getId();
    }

    public List<Chatroom> getRoomsByUser(User u) {
        //TODO FIX THIS SHIT
        //Query q = manager.createQuery("select r from Chatroom r where :id in (select member.members from C r.members )");
        //q.setParameter(u.getId())
        return null;
    }

    public List<User> getUsersInroom(Chatroom room)
    {
        //TODO THIS TO !
        return null;
    }


    Chatroom getChatRoomById(long id) {
        System.out.println("TRYING TO FETCH CAHTRMUM");
        Chatroom unsortedChatroom = manager.find(Chatroom.class, id);
        System.out.println("BEFORE: " + unsortedChatroom);
        unsortedChatroom.getMessages().sort(new ChatMessageSorter());
        System.out.println("AFTER: " + unsortedChatroom);
        return unsortedChatroom;
    }

    // Paketpublik metod f|r att posta till en chatt. Kastar lite exceptions.
    void postChatMessage(long chatroomId, User poster, String text) throws ChatException, UserException {
        if (poster == null) throw new UserException("User null when trying to post to chatroom.");
        Chatroom roomToPostIn = getChatRoomById(chatroomId);
        if (roomToPostIn == null) throw new ChatException("Chatroom not found");
        if (!roomToPostIn.getMemberIds().contains(poster)) throw new ChatException("User not member of chat!");
        postChatMessage(new ChatMessage(text, new Date(), roomToPostIn, poster.getEmail()));
    }

    // Privat metod f|r att posta till en chatt. Kontaktar databasen.
    private void postChatMessage(ChatMessage message) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(message);
            trans.commit();
        } catch (PersistenceException pe) {
            if (trans != null) trans.rollback();
            pe.printStackTrace();
        }
    }

    void addUserToChatroom(long chatRoomId, User userToAdd) throws UserException, ChatException {
        if (userToAdd == null) throw new UserException("User null when adding to chatroom.");
        Chatroom room = getChatRoomById(chatRoomId);
        if (room == null) throw new ChatException("Chatroom not found when adding user to chatroom.");
        addUserToChatroom(room, userToAdd);
    }

    private void addUserToChatroom(Chatroom room, User user) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            room.getMemberIds().add(user.getId());
            user.getRoomIds().add(room.getId());
            manager.persist(room);
            manager.persist(user);
            trans.commit();
        } catch (PersistenceException pe) {
            if (trans != null) trans.rollback();
            pe.printStackTrace();
        }
    }
}
