package com.endpoint;

import com.bo.ChatFacade;
import com.viewmodels.requestviews.ChatPostRequest;
import com.viewmodels.requestviews.CreateChatroomRequest;
import com.viewmodels.resultviews.CreateChatRoomResult;
import com.viewmodels.resultviews.GetChatroomResult;
import com.viewmodels.resultviews.ListChatRoomsResult;
import com.viewmodels.resultviews.Result;

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
    public Result addUserToChatroom(@QueryParam("roomId") String roomId, @QueryParam("userId") String userId) {
        return new ChatFacade().addUserToChatroom(roomId,userId);
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public GetChatroomResult getChatRoomById(@QueryParam("roomId") String roomId) {
        return new ChatFacade().getChatroomById(roomId);
    }
}
