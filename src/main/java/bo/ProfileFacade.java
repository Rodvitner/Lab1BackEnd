package bo;

import model.Post;
import model.User;
import viewmodels.CreatePostResult;
import viewmodels.CreatePostView;
import viewmodels.UserView;

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
        EntityTransaction transa = prylchef.getTransaction();
        transa.begin();
        User userFromDB = prylchef.find(User.class,email);
        transa.commit();
        UserView toReturn= new UserView(userFromDB.getName(),userFromDB.getEmail(),null);
        prylchef.close();
        return toReturn;
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

    private void abort() {
        // TODO: 2016-11-20 abort transaction in som smart way
    }

}
