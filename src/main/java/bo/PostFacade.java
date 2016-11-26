package bo;

import bo.translators.PostPostViewMapper;
import exception.PostException;
import exception.UserException;
import model.Post;
import model.User;
import viewmodels.generalviews.PostView;
import viewmodels.resultviews.WallResult;
import viewmodels.requestviews.CreatePostRequest;
import viewmodels.requestviews.WallRequest;
import viewmodels.resultviews.CreatePostResult;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by simonlundstrom on 22/11/16.
 */
public class PostFacade {
    private EntityManager prylchef;

    public PostFacade() {
        prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public CreatePostResult createPost(CreatePostRequest postToCreate) {
        CreatePostResult res;

        User userThatPosted;

        try {
            userThatPosted = new UserLogic(prylchef).findUserByEmail(postToCreate.getUserEmail());
            int id = new PostLogic(prylchef).post(postToCreate.getPostText(), userThatPosted, new Date());
            res = new CreatePostResult(true, "Success!", id);
        } catch (UserException ue) {
            res = new CreatePostResult(false, ue.getMessage(), -1);
        } catch (PostException pe) {
            res = new CreatePostResult(false, pe.getMessage(), -1);
            res.setId(-1);
        } finally {
            prylchef.close();
        }
        return res;
    }

    public WallResult getWall(WallRequest wallRequest) {
        WallResult res = null;

        User requestingUser = null;

        List<Post> list = null;
        try {
            requestingUser = new UserLogic(prylchef).findUserByEmail(wallRequest.getUserEmail());
            list = new PostLogic(prylchef).listPosts(requestingUser, wallRequest.getStartAt(), wallRequest.getAmountOfPosts());
            System.out.println("By some reason or another, the JPQL worked... I think.");
            res = new WallResult(true, "Success!", new PostPostViewMapper().translateListOfA(list));
        } catch (UserException ue) {
            res = new WallResult(false, ue.getMessage(), null);
            ue.printStackTrace();
        } catch (PostException pe) {
            res = new WallResult(false, pe.getMessage(), null);
        } finally {
            prylchef.close();
        }
        return res;
    }

    private void abort() {
        // TODO: 22/11/16  abort transaction in some fancy way.
    }

    public List<PostView> getPostsByuser(String userid) {
        List<PostView> list = new ArrayList<>();
        try {
            User u = new UserLogic(prylchef).findUserByEmail(userid);
            list = new PostPostViewMapper().translateListOfA(u.getPosts());

        } catch (UserException e) {
            e.printStackTrace();

        }

        return list;
    }
}
