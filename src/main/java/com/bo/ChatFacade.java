package com.bo;

import com.bo.translators.ChatroomChatroomViewMapper;
import com.bo.translators.ChatroomMetaDataMapper;
import com.exception.ChatException;
import com.exception.UserException;
import com.google.appengine.api.datastore.KeyFactory;
import com.model.Chatroom;
import com.model.User;
import com.viewmodels.requestviews.ChatPostRequest;
import com.viewmodels.requestviews.CreateChatroomRequest;
import com.viewmodels.resultviews.CreateChatRoomResult;
import com.viewmodels.resultviews.GetChatroomResult;
import com.viewmodels.resultviews.ListChatRoomsResult;
import com.viewmodels.resultviews.Result;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatFacade {
    EntityManager prylchef;

    public ChatFacade() { prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public CreateChatRoomResult createChatRoom(CreateChatroomRequest request) {
        CreateChatRoomResult res = null;
        try {
            User creator = new UserLogic(prylchef).findUserByEmail(request.getOwnerEmail());
            User invitee = new UserLogic(prylchef).findUserByEmail(request.getInviteeEmail());
            res = new CreateChatRoomResult(true,"OK", KeyFactory.keyToString(new ChatLogic(prylchef).createChatroom(creator,invitee,request.getRoomName())));
        }catch(UserException ue) {
            res = new CreateChatRoomResult(false,ue.getMessage(),null);
        }finally{
            prylchef.close();
        }
        return res;
    }

    public ListChatRoomsResult listChatroomMetaDataByUser(String userId) {
        ListChatRoomsResult res = null;
        try {
            User user = new UserLogic(prylchef).findUserByEmail(userId);
            List<Chatroom> chatrooms = new ChatLogic(prylchef).getRoomsByUser(user);
            res = new ListChatRoomsResult(true,"OK",new ChatroomMetaDataMapper().translateListOfA(chatrooms));
        }catch(UserException ue) {
            res = new ListChatRoomsResult(false, ue.getMessage(),null);
        }finally{
            prylchef.close();
        }
        return res;
    }

    public GetChatroomResult getChatroomById(String id){
        GetChatroomResult res = null;
        Chatroom room;
        try {
            System.out.println("TRYING TO FIND CHAT  WITH KEY " + id);
            room = new ChatLogic(prylchef).getChatRoomById(KeyFactory.stringToKey(id));
            res = new GetChatroomResult(true,"OK",new ChatroomChatroomViewMapper().translateFromA(room));
        }finally {
            prylchef.close();
        }
        System.out.println("GOT ROOM "+ room + " RES IS " + res);
        return res;
    }

    public Result postToChat(ChatPostRequest request) {
        Result res = null;
        try {
            User poster = new UserLogic(prylchef).findUserByEmail(request.getPosterId());
            new ChatLogic(prylchef).postChatMessage(KeyFactory.stringToKey(request.getChatroomId()),poster,request.getText());
            res = new Result(true,"Posted to chatroom.");
        }catch (UserException ue) {
            res = new Result(false,ue.getMessage());
        }catch(ChatException ce) {
            res = new Result(false,ce.getMessage());
        }finally {
            prylchef.close();
        }
        return res;
    }

    public Result addUserToChatroom(String chatroomId, String userId) {
        Result res=null;
        try {
            User userToAdd = new UserLogic(prylchef).findUserByEmail(userId);
            new ChatLogic(prylchef).addUserToChatroom(KeyFactory.stringToKey(chatroomId),userToAdd);
            res = new Result(true,"Added user to chat!");
        }catch(UserException ue) {
            res = new Result(false,ue.getMessage());
        }catch(ChatException ce) {
            res = new Result(false, ce.getMessage());
        }finally{
            prylchef.close();
        }
        return res;
    }
}
