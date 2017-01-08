package com.bo;

import com.bo.translators.PostPostViewMapper;
import com.exception.PostException;
import com.exception.UserException;
import com.google.appengine.api.datastore.Key;
import com.model.Post;
import com.model.User;
import com.viewmodels.generalviews.PostView;
import com.viewmodels.resultviews.GetPostResult;
import com.viewmodels.resultviews.WallResult;
import com.viewmodels.requestviews.CreatePostRequest;
import com.viewmodels.requestviews.WallRequest;
import com.viewmodels.resultviews.CreatePostResult;

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
            Key id = new PostLogic(prylchef).post(postToCreate.getPostText(), userThatPosted, new Date());
            res = new CreatePostResult(true, "Success!", id);
        } catch (UserException ue) {
            res = new CreatePostResult(false, ue.getMessage(), null);
        } catch (PostException pe) {
            res = new CreatePostResult(false, pe.getMessage(), null);
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

    public GetPostResult getPostById(int postId) {
        GetPostResult res = null;
        Post p = new PostLogic(prylchef).getPostById(postId);
        if (p==null)
            res = new GetPostResult(false,"Could not find post.",null);
        else
            res = new GetPostResult(true,"post found.",new PostPostViewMapper().translateFromA(p));
        return res;
    }
}
