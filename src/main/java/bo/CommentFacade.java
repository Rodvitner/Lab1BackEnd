package bo;

import bo.translators.CommentCommentViewMapper;
import exception.PostException;
import exception.UserException;
import model.Comment;
import model.Post;
import model.User;
import viewmodels.generalviews.CommentView;
import viewmodels.requestviews.CreateCommentRequest;
import viewmodels.resultviews.CommentListResult;
import viewmodels.resultviews.Result;

import javax.persistence.EntityManager;
import java.util.List;

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
            new CommentLogic(prylchef).postComment(ccr.getText(), poster, post);
            res = new Result(true,"Comment posted.");
        } catch (UserException ue) {
            res = new Result(false, ue.getMessage());
        } catch (PostException pe) {
            res = new Result(false, pe.getMessage());
        } finally {
            prylchef.close();
        }
        return res;
    }

    public CommentListResult getCommentsByPostId(int id) {
        CommentListResult res = null;
        List<Comment> lista = new CommentLogic(prylchef).getCommentsByPostId(id);
        if (lista==null)
            res = new CommentListResult(false,"No Comments.",null);
        else
            res = new CommentListResult(true,"OK",new CommentCommentViewMapper().translateListOfA(lista));
        prylchef.close();
        return res;
    }
}
