package bo;

import exception.PostException;
import model.Post;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Date;

/**
 * Created by simonlundstrom on 22/11/16.
 */
class PostLogic {
    private EntityManager manager;

    PostLogic(EntityManager em) {
        this.manager=em;
    }

    //Paketpublik metod for att posta.
    int post(String text, User user, Date date) throws PostException {

        // OBS! Denna metod {r BARA post-logik. Den g|r ingen koll f|r User; det g|r fasaden.
        // Det borde vara s} det {r t{nkt...

        if (text==null || text.length()<1) {
            throw new PostException("No message to post.");
        }
        if (date==null) {
            throw new PostException("No date on post.");
        }
        // At this point, the method assumes it can create the post.
        Post newPost = new Post(text,date,user);
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(newPost);
            trans.commit();
        } catch (PersistenceException ex) {
            if (trans!=null) trans.rollback();
            ex.printStackTrace();
            throw ex;
        }
        return newPost.getId();
    }
}
