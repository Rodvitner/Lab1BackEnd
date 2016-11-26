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

    // Paketpublik metod f|r att h{mta en post baserat p} id.
    Post getPostById(int postId) {
        return manager.find(Post.class,postId);
    }

    //Paketpublik metod for att posta.
    int post(String text, User user, Date date) throws PostException,UserException {

        if (text == null || text.length() < 1) {
            throw new PostException("Message null or empty when posting.");
        }
        if (date == null) {
            throw new PostException("No date on post when posting");
        }
        if (user == null) {
            throw new UserException("Null user when posting.");
        }
        return post(new Post(text, date, user));
    }

    // Privat metod f|r att posta, kontaktar databasen.
    int post(Post post) {
        EntityTransaction trans = null;
        try {
            trans = manager.getTransaction();
            trans.begin();
            manager.persist(post);
            trans.commit();
        } catch (PersistenceException ex) {
            if (trans!=null) trans.rollback();
            ex.printStackTrace();
            throw ex;
        }
        return post.getId();
    }

    //Paketpublik metod f|r att f} en v{gg.
    public List<Post> listPosts(User user, int startAt, int amountOfPosts) throws UserException,PostException {
        if (user ==null) throw new UserException("Null user when listing posts.");
        if (startAt<0) throw new PostException("Negative number of the post to start listing points at.");
        if (amountOfPosts<0) throw new PostException("Less than zero posts to show when listing posts.");

        // H{r ska det g} att h{mta en lista.
        List<Post> ret = new LinkedList<Post>();
        Query q = manager.createQuery("SELECT p FROM Post p WHERE p.user in :email OR p.user=:me ORDER BY p.date desc ")
                .setFirstResult(startAt)
                .setMaxResults(amountOfPosts);
        System.out.println("RESULT OF USER FRIENDLIST: "+user.getFriends()+", LENGTH="+user.getFriends().size());
        if (user.getFriends().size()<1)
            q.setParameter("email",user);
        else
            q.setParameter("email",user.getFriends());
        q.setParameter("me",user);

        ret = (List<Post>)q.getResultList();
        return ret;
    }
}
