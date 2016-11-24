package endpoint;

import bo.FriendFacade;
import viewmodels.requestviews.BefriendRequest;
import viewmodels.resultviews.BefriendUserResult;
import viewmodels.resultviews.FriendListResult;
import viewmodels.resultviews.GetUserResult;
import viewmodels.resultviews.Result;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

    @GET
    @Path("friendlist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FriendListResult listFriends(@QueryParam("id") String id) {
        return new FriendFacade().listFriends(id);
    }

    @PUT
    @Path("removeFriend")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result removeFriend(BefriendRequest enemies) {
        return new FriendFacade().removeFriend(enemies);
    }
}
