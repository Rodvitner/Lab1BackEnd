package bo;

import exception.PostException;
import exception.UserException;
import model.Post;
import model.User;
import viewmodels.requestviews.CreateCommentRequest;
import viewmodels.resultviews.Result;

import javax.persistence.EntityManager;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class CommentFacade {
    EntityManager prylchef;
    public CommentFacade() {
        prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public Result postComment(CreateCommentRequest ccr) {
        Result res = null;
        User poster = null;
        Post post = null;
        try {
            poster = new UserLogic(prylchef).findUserByEmail(ccr.getUserEmail());
            post = new PostLogic(prylchef).getPostById(ccr.getPostId());
            new CommentLogic(prylchef).postComment(ccr.getText(),poster,post);
        } catch(UserException ue) {
            res = new Result(false,ue.getMessage());
        }catch(PostException pe){
            res = new Result(false,pe.getMessage());
        }finally{
            prylchef.close();
        }
        return res;

    }
}
