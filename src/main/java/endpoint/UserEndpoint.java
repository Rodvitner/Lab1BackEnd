package endpoint;

import bo.LocalEntityManagerFactory;
import bo.ProfileFacade;
import viewmodels.UserView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Path("/user")
public class UserEndpoint {

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserView displayUser(@PathParam("id") String email) {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
        return new ProfileFacade(new LocalEntityManagerFactory().createEntityManager()).getUserById(email);
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserView createUser(UserView newUser) {
        return new ProfileFacade(new LocalEntityManagerFactory().createEntityManager()).createUser(newUser);

    }


}