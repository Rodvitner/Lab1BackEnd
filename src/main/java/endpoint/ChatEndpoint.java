package endpoint;

import bo.ChatFacace;
import viewmodels.requestviews.CreateChatroomRequest;
import viewmodels.resultviews.CreateChatRoomResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        return new ChatFacace().createChatRoom(request);
    }
}
