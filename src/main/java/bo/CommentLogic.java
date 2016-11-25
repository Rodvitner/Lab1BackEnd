package bo;

import exception.PostException;
import exception.UserException;
import model.Comment;
import model.Post;
import model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CommentLogic {
    private EntityManager manager;

    CommentLogic(EntityManager em) {
        manager = em;
    }

    // Paketpublik metod f|r att posta en kommentar.
    void postComment(String text, User user, Post post) throws PostException, UserException {
        if (user == null) throw new UserException("Null user.");
        if (text == null || text.length()<1) throw new PostException("Comment body null or empty.");
        Comment commentToPost=new Comment(text,new Date(),user,post);
        postComment(commentToPost);
    }

    // Privat metod som postar posten till databasen.
    private void postComment(Comment comment) {
        EntityTransaction trans= null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(comment);
            trans.commit();
        }
        catch (PersistenceException pe) {
            if (trans!=null) trans.rollback();
            pe.printStackTrace();
        }
    }

    // Public metod f|r att lista alla kommentare p} en post.
    public List<Comment> getCommentsByPostId(int id) {
        Query q = manager.createQuery("SELECT c from Comment c WHERE c.post.id=:id");
        q.setParameter("id",id);
        List<Comment> res = (List<Comment>)q.getResultList();
        return res;
    }
}
