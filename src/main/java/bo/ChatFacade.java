package bo;

import bo.translators.ChatroomChatroomViewMapper;
import bo.translators.ChatroomMetaDataMapper;
import exception.ChatException;
import exception.UserException;
import model.Chatroom;
import model.User;
import viewmodels.requestviews.ChatPostRequest;
import viewmodels.requestviews.CreateChatroomRequest;
import viewmodels.resultviews.CreateChatRoomResult;
import viewmodels.resultviews.GetChatroomResult;
import viewmodels.resultviews.ListChatRoomsResult;
import viewmodels.resultviews.Result;

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
            res = new CreateChatRoomResult(true,"OK",new ChatLogic(prylchef).createChatroom(creator,invitee,request.getRoomName()));
        }catch(UserException ue) {
            res = new CreateChatRoomResult(false,ue.getMessage(),-1);
        }finally{
            prylchef.close();
        }
        return res;
    }

    public ListChatRoomsResult listChatroomMetaDataByUser(String userId) {
        ListChatRoomsResult res = null;
        try {
            User user = new UserLogic(prylchef).findUserByEmail(userId);
            List<Chatroom> chatrooms = user.getRooms();
            res = new ListChatRoomsResult(true,"OK",new ChatroomMetaDataMapper().translateListOfA(chatrooms));
        }catch(UserException ue) {
            res = new ListChatRoomsResult(false, ue.getMessage(),null);
        }finally{
            prylchef.close();
        }
        return res;
    }

    public GetChatroomResult getChatroomById(int id){
        GetChatroomResult res = null;
        try {
            Chatroom room = new ChatLogic(prylchef).getChatRoomById(id);
            res = new GetChatroomResult(true,"OK",new ChatroomChatroomViewMapper().translateFromA(room));
        }finally {
            prylchef.close();
        }
        return res;
    }

    public Result postToChat(ChatPostRequest request) {
        Result res = null;
        try {
            User poster = new UserLogic(prylchef).findUserByEmail(request.getPosterId());
            new ChatLogic(prylchef).postChatMessage(request.getChatroomId(),poster,request.getText());
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

    public Result addUserToChatroom(int chatroomId, String userId) {
        Result res=null;
        try {
            User userToAdd = new UserLogic(prylchef).findUserByEmail(userId);
            new ChatLogic(prylchef).addUserToChatroom(chatroomId,userToAdd);
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
