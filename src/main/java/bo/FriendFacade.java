package bo;

import bo.translators.UserUserViewMapper;
import exception.UserException;
import model.User;
import viewmodels.requestviews.BefriendRequest;
import viewmodels.resultviews.BefriendUserResult;
import viewmodels.resultviews.FriendListResult;
import viewmodels.resultviews.GetUserResult;
import viewmodels.resultviews.Result;

import javax.persistence.EntityManager;
import java.util.List;

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

    public FriendListResult listFriends(String id) {
        FriendListResult res;
        User asker;
        try {
            asker = new UserLogic(prylchef).findUserByEmail(id);
            System.out.println("FriendFacade tried to find user on id="+id);
            if (asker==null)
                res = new FriendListResult(false,"FriendFacade: No such user.",null);
            else
                res = new FriendListResult(true,"OK", new UserUserViewMapper()
                    .translateListOfA(asker.getFriends()));
        }catch(UserException ue){
            res = new FriendListResult(false,ue.getMessage(),null);
        }finally{
            prylchef.close();
        }
        return res;
    }

    public Result removeFriend(BefriendRequest enemies) {
        Result res = null;
        try {
            new UserLogic(prylchef).removeRelation(enemies.getUser1Email(),enemies.getUser2Email());
            res = new Result(true,enemies.getUser1Email()+" and "+enemies.getUser2Email()+" are no longer friends.");
        }catch(UserException ue) {
            res = new Result(false,ue.getMessage());
        }finally{
            prylchef.close();
        }
        return res;
    }
}
