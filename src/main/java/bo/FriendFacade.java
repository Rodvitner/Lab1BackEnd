package bo;

import exception.UserException;
import viewmodels.requestviews.BefriendRequest;
import viewmodels.resultviews.BefriendUserResult;

import javax.persistence.EntityManager;

/**
 * Created by simonlundstrom on 22/11/16.
 */
public class FriendFacade {
    private EntityManager prylchef;

    public FriendFacade() {
        prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public BefriendUserResult addFriend(BefriendRequest req){
        BefriendUserResult res=null;
        try{
            new UserLogic(prylchef).makeFriends(req.getUser1Email(), req.getUser2Email());
            res = new BefriendUserResult(true,"success");
        }catch(UserException e) {
            res = new BefriendUserResult(false,e.getMessage());
        }finally {
            prylchef.close();
        }
        return res;
    }
}
