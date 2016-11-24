package endpoint;

import bo.ChatFacade;
import viewmodels.requestviews.ChatPostRequest;
import viewmodels.requestviews.CreateChatroomRequest;
import viewmodels.resultviews.CreateChatRoomResult;
import viewmodels.resultviews.GetChatroomResult;
import viewmodels.resultviews.ListChatRoomsResult;
import viewmodels.resultviews.Result;

import javax.print.attribute.standard.Media;
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

    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result postToChatRoom(ChatPostRequest request) {
        return new ChatFacade().postToChat(request);
    }

    @PUT
    @Path("adduser")
    @Produces(MediaType.APPLICATION_JSON)
    public Result addUserToChatroom(@QueryParam("roomId") int roomId, @QueryParam("userId") String userId) {
        return new ChatFacade().addUserToChatroom(roomId,userId);
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public GetChatroomResult getChatRoomById(@QueryParam("roomId") int roomId) {
        return new ChatFacade().getChatroomById(roomId);
    }
}
