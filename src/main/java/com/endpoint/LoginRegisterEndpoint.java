package com.endpoint;

import com.bo.LoginRegisterFacade;
import com.viewmodels.requestviews.CreateUserRequest;
import com.viewmodels.requestviews.LoginRequest;
import com.viewmodels.resultviews.CreateUserResult;
import com.viewmodels.resultviews.LoginResult;
import com.viewmodels.resultviews.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Result logoutUser(@QueryParam("id") String userToLogout) {
        return new LoginRegisterFacade().logoutUser(userToLogout);
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