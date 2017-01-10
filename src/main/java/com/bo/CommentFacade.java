package com.bo;

import com.bo.translators.CommentCommentViewMapper;
import com.exception.PostException;
import com.exception.UserException;
import com.google.appengine.api.datastore.KeyFactory;
import com.model.Comment;
import com.model.Post;
import com.model.User;
import com.viewmodels.requestviews.CreateCommentRequest;
import com.viewmodels.resultviews.CommentListResult;
import com.viewmodels.resultviews.Result;

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
            post = new PostLogic(prylchef).getPostById(KeyFactory.stringToKey(ccr.getPostId()));
            System.out.println("GOT POST " + post + " WITH ID NR " + ccr.getPostId());
            new CommentLogic(prylchef).postComment(ccr.getText(), poster, post);
            res = new Result(true,"success");
        } catch (UserException ue) {
            res = new Result(false, ue.getMessage());
        } catch (PostException pe) {
            res = new Result(false, pe.getMessage());
        } finally {
            prylchef.close();
        }
        System.out.println("RESULT OF CREATE COMNMENT IS " + res);
        return res;
    }

    public CommentListResult getCommentsByPostId(String id) {
        CommentListResult res = null;
        List<Comment> lista = new CommentLogic(prylchef).getCommentsByPostId(KeyFactory.stringToKey(id));
        System.out.println("LISTA AV KLOMMENTARER ÄR " + lista);
        if (lista==null || lista.size() <= 0 )
            res = new CommentListResult(false,"No Comments.",null);
        else
            res = new CommentListResult(true,"OK",new CommentCommentViewMapper().translateListOfA(lista));
        prylchef.close();
        return res;
    }
}
