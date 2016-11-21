package bo;

import exception.UserException;
import model.Post;
import model.User;
import viewmodels.requestViews.BefriendRequest;
import viewmodels.requestViews.CreatePostView;
import viewmodels.resultviews.BefriendUserResult;
import viewmodels.resultviews.CreatePostResult;
import viewmodels.resultviews.UserView;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class ProfileFacade {
    EntityManager prylchef;

    public ProfileFacade(EntityManager em) {
        prylchef = em;
    }

    public UserView createUser(UserView incoming) {
        EntityTransaction transa = prylchef.getTransaction();
        transa.begin();
        User toBeSaved = new User();
        toBeSaved.setEmail(incoming.getEmail());
        toBeSaved.setName(incoming.getName());
        toBeSaved.setPassword(incoming.getPassword());
        prylchef.persist(toBeSaved);
        transa.commit();
        prylchef.close();
        return new UserView(toBeSaved.getName(),toBeSaved.getEmail(),null);
    }

    public UserView getUserById(String email) {
        User userFromDB= new UserLogic(prylchef).getUser(email);
        if(userFromDB == null){
            userFromDB = new User();
        }
        prylchef.close();
        return new UserView(userFromDB.getName(),userFromDB.getEmail(),null);

    }

    public CreatePostResult createPost(CreatePostView postToCreate){
        CreatePostResult res = new CreatePostResult();

        EntityTransaction transa = prylchef.getTransaction();
        transa.begin();

        User creator = prylchef.find(User.class, postToCreate.getUserEmail());
        if(creator == null && creator.getName() == null){
            res.setStatus("NONEXISTANT USER");
            abort();
            return res;

        }
        Post p = new Post();
        p.setDate(postToCreate.getPostDate());
        p.setText(postToCreate.getPostText());
        p.setUser(creator);
        prylchef.persist(p);

        res.setStatus("success");
        res.setId(p.getId());
        transa.commit();
        prylchef.close();

       return res;

    }

    public BefriendUserResult addFriend(BefriendRequest req){
        BefriendUserResult res = new BefriendUserResult();
        try{
            new UserLogic(prylchef).makeFriends(req.getUser1Email(), req.getUser2Email());
            res.setReason("success");
            res.setSuccess(true);
        }catch(UserException e) {
            res.setSuccess(false);
            res.setReason(e.getMessage());
        }finally {
            prylchef.close();
        }

        return res;
    }

    private void abort() {
        // TODO: 2016-11-20 abort transaction in som smart way
    }

}
