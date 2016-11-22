package bo;

import exception.PostException;
import model.Post;
import model.User;
import viewmodels.requestviews.CreatePostRequest;
import viewmodels.resultviews.CreatePostResult;

import javax.persistence.EntityManager;

/**
 * Created by simonlundstrom on 22/11/16.
 */
public class PostFacade {
    private EntityManager prylchef;

    public PostFacade() {
        prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public CreatePostResult createPost(CreatePostRequest postToCreate) {
        CreatePostResult res = new CreatePostResult();

        User userThatPosted = new UserLogic(prylchef).findUserByEmail(postToCreate.getUserEmail());
        if (userThatPosted == null) {
            res.setStatus("Non-existent user.");
            abort();
            res.setId(-1);
            return res;
        }

        try {
            int id = new PostLogic(prylchef).post(postToCreate.getPostText(), userThatPosted, postToCreate.getPostDate());
            res.setId(id);
            res.setStatus("Success!");
        } catch (PostException pe) {
            res.setStatus(pe.getMessage());
            res.setId(-1);
        } finally {
            prylchef.close();
        }
        return res;
    }

    private void abort() {
        // TODO: 22/11/16  abort transaction in some fancy way.
    }
}
