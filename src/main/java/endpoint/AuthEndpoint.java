package endpoint;

import bo.LoginRegisterFacade;
import viewmodels.requestviews.AuthRequest;
import viewmodels.resultviews.Result;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by archer on 2016-12-15.
 */

@Path("auth")
public class AuthEndpoint {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result auth(AuthRequest auth){
        return new LoginRegisterFacade().authenticate(auth);
    }

}
