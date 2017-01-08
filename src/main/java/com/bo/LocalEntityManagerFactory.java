package com.bo;

import com.viewmodels.requestviews.CreateUserRequest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by simonlundstrom on 16/11/16.
 */

@WebListener
public class LocalEntityManagerFactory implements ServletContextListener {
    private static EntityManagerFactory fabrik;


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (fabrik == null) {
            System.out.println("INITIALIZING ENTITIY MANAGER FACTORYU");
            // get map for environment overrides of db settings
            Map<String, String> configOverrides = new HashMap<>();
            String username = System.getenv("DBUSER");
            String password = System.getenv("DBPASS");
            String dburl = System.getenv("DBURL");

            if (username != null) {
                configOverrides.put("javax.persistence.jdbc.user", username);
            }
            if (password != null) {
                configOverrides.put("javax.persistence.jdbc.password", password);
            }
            if (dburl != null) {
                configOverrides.put("javax.persistence.jdbc.url", dburl);
            }
            //create with overrides
            fabrik = Persistence.createEntityManagerFactory("LocalSQL", configOverrides);
            // insert test data
            new LoginRegisterFacade().createUser(new CreateUserRequest("Pelle", "pelle@pelle.pe", "potatis"));
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (fabrik != null)
            fabrik.close();
    }

    public EntityManager createEntityManager() {
        EntityManager newEntityManager = null;
        synchronized (this) {
            if (fabrik == null) {
                contextInitialized(null);
                //throw new IllegalStateException();
            }
            newEntityManager = fabrik.createEntityManager();
        }
        return newEntityManager;
    }
}
