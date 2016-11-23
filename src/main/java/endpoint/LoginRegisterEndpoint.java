package endpoint;

import bo.LoginRegisterFacade;
import viewmodels.requestviews.CreateUserRequest;
import viewmodels.requestviews.LoginRequest;
import viewmodels.resultviews.CreateUserResult;
import viewmodels.resultviews.LoginResult;
import viewmodels.resultviews.GetUserResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simonlundstrom on 16/11/16.
 */
@Path("/user")
public class LoginRegisterEndpoint {

    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateUserResult createUser(CreateUserRequest newUser) {
        return new LoginRegisterFacade().createUser(newUser);

    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResult loginUser(LoginRequest userToLogin) {
        return new LoginRegisterFacade().loginUser(userToLogin);
    }

    /*
    // Deprecated? Wrong place?
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateUserRequest displayUser(@PathParam("id") String email) {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
        return new LoginRegisterFacade(new LocalEntityManagerFactory().createEntityManager()).getUserById(email);
    }
    */
}