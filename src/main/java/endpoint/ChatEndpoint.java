package endpoint;

import bo.ChatFacade;
import viewmodels.requestviews.CreateChatroomRequest;
import viewmodels.resultviews.CreateChatRoomResult;
import viewmodels.resultviews.ListChatRoomsResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by simonlundstrom on 24/11/16.
 */
@Path("/chat")
public class ChatEndpoint {
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateChatRoomResult createChatRoom(CreateChatroomRequest request){
        return new ChatFacade().createChatRoom(request);
    }

    @GET
    @Path("listmyrooms")
    @Produces(MediaType.APPLICATION_JSON)
    public ListChatRoomsResult listChatroomsByUser(@QueryParam("user") String userId) {
        return new ChatFacade().listChatroomMetaDataByUser(userId);
    }
}
