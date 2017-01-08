package com.endpoint;

import com.bo.ListFacade;
import com.viewmodels.requestviews.ListUsersRequest;
import com.viewmodels.generalviews.UserView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
@Path("/user")
public class ListUsersEndPoint {
    @GET
    @Path("listusers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<UserView> listUsers(@QueryParam("searchString") String searchString, @QueryParam("from") int from, @QueryParam("amount") int amount) {
        return new ListFacade().listUsers(new ListUsersRequest(searchString,from,amount));
    }
}
