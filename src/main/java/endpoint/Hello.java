package endpoint;

import bo.LocalEntityManagerFactory;
import bo.UserFacade;
import viewmodels.UserView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Path("/")
public class Hello {
    @GET
    public String displayUser() {
        return new UserFacade(new LocalEntityManagerFactory().createEntityManager()).saveUser(new UserView("Pelle","pelle@pelle.pe","potatis")).toString();
    }
}
