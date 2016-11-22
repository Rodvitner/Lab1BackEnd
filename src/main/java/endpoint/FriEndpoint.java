package endpoint;

import bo.FriendFacade;
import viewmodels.requestviews.BefriendRequest;
import viewmodels.resultviews.BefriendUserResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by simonlundstrom on 22/11/16.
 * Jag kunde inte st} emot ordvitsen...
 */
@Path("/user")
public class FriEndpoint {
    @POST
    @Path("addfriend")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BefriendUserResult befriendUser(BefriendRequest br) {
        return new FriendFacade().addFriend(br);
    }
}
