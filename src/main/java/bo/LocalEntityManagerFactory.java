package bo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by simonlundstrom on 16/11/16.
 */

@WebListener
public class LocalEntityManagerFactory implements ServletContextListener {
    private static EntityManagerFactory fabrik;
    int pelle;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (fabrik == null)
            fabrik = Persistence.createEntityManagerFactory("LocalSQL");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (fabrik != null)
            fabrik.close();
    }

    public EntityManager createEntityManager() {
        if (fabrik==null) throw new IllegalStateException();
        return fabrik.createEntityManager();
    }
}
