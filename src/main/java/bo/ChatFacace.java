package bo;

import exception.UserException;
import model.User;
import viewmodels.requestviews.CreateChatroomRequest;
import viewmodels.resultviews.CreateChatRoomResult;

import javax.persistence.EntityManager;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatFacace {
    EntityManager prylchef;

    public ChatFacace() { prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public CreateChatRoomResult createChatRoom(CreateChatroomRequest request) {
        CreateChatRoomResult res = null;
        try {
            User creator = new UserLogic(prylchef).findUserByEmail(request.getOwnerEmail());
            User invitee = new UserLogic(prylchef).findUserByEmail(request.getInviteeEmail());
            res = new CreateChatRoomResult(true,"OK",new ChatLogic(prylchef).createChatroom(creator,invitee));
        }catch(UserException ue) {
            res = new CreateChatRoomResult(false,ue.getMessage(),-1);
        }finally{
            prylchef.close();
        }
        return res;
    }
}
