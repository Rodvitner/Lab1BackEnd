package endpoint;

import viewmodels.requestViews.LoginMessage;
import viewmodels.resultviews.LoginStatusReturnMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by simonlundstrom on 19/11/16.
 */
@Path("/login")
public class Login {
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginStatusReturnMessage ReturnTrue(LoginMessage loginMessage) {
        return new LoginStatusReturnMessage(true,"OK");
    }
}
