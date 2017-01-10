package com.bo;

import com.exception.PostException;
import com.exception.UserException;
import com.google.appengine.api.datastore.Key;
import com.model.Comment;
import com.model.Post;
import com.model.User;

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

    // Paketpublik metod f|r att posta en kommentar. Kastar exceptions.
    void postComment(String text, User user, Post post) throws PostException, UserException {
        if (user == null) throw new UserException("Null user.");
        if (text == null || text.length() < 1) throw new PostException("Comment body null or empty.");
        Comment commentToPost = new Comment(text, new Date(), user.getEmail(), post);
        postComment(commentToPost);
    }

    // Privat metod som postar posten till databasen.
    private void postComment(Comment comment) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(comment);
            trans.commit();
        } catch (PersistenceException pe) {
            if (trans != null) trans.rollback();
            pe.printStackTrace();
        }
    }

    // Public metod f|r att lista alla kommentare p} en post.
    public List<Comment> getCommentsByPostId(Key id) {

        //Query q = manager.createQuery("SELECT c from Comment c WHERE c.post.id=:id");
        Query q = manager.createQuery("Select p from Post p where p.id = :id ");
        q.setParameter("id", id);

        Post p;
        try {
            p = (Post) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        List<Comment> res = p.getComments();
        return res;
    }
}
