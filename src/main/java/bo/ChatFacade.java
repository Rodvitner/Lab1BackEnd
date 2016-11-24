package bo;

import bo.translators.ChatroomChatroomViewMapper;
import bo.translators.ChatroomMetaDataMapper;
import exception.UserException;
import model.Chatroom;
import model.User;
import viewmodels.requestviews.CreateChatroomRequest;
import viewmodels.resultviews.CreateChatRoomResult;
import viewmodels.resultviews.GetChatroomResult;
import viewmodels.resultviews.ListChatRoomsResult;

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
            res = new CreateChatRoomResult(true,"OK",new ChatLogic(prylchef).createChatroom(creator,invitee, request.getRoomName()));
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
            List<Chatroom> chatrooms = new ChatLogic(prylchef).listChatroomsByUser(user);
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
}
