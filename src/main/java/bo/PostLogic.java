package bo;

import exception.PostException;
import exception.UserException;
import model.Post;
import model.User;
import org.hibernate.hql.spi.QueryTranslatorFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    //Paketpublik metod f|r att f} en v{gg.
    public List<Post> listPosts(User user, int startAt, int amountOfPosts) throws UserException,PostException {
        // Kollar inte om Usern finns; den r{knar med att fasaden har h{mtat en redan.
        if (startAt<0) throw new PostException("Negative number of the post to start the list at.");
        if (amountOfPosts<0) throw new PostException("Less than zero posts to show.");

        // H{r ska det g} att h{mta en lista.
        List<Post> ret = new LinkedList<Post>();
        Query q = manager.createQuery("SELECT p FROM Post p WHERE p.user=:email OR p.user=:me ORDER BY p.date")
                .setFirstResult(startAt)
                .setMaxResults(amountOfPosts);
        q.setParameter("email",user.getFriends());
        q.setParameter("me",user);

        ret = (List<Post>)q.getResultList();
/*
        q = manager.createQuery("SELECT p FROM Post p WHERE p.user=:email");
        q.setParameter("email",user);
        ret.addAll((List<Post>)q.getResultList());
        */
        return ret;
    }
}
