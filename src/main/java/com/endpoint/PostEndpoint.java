package com.endpoint;

import com.bo.PostFacade;
import com.viewmodels.generalviews.PostView;
import com.viewmodels.resultviews.GetPostResult;
import com.viewmodels.resultviews.WallResult;
import com.viewmodels.requestviews.WallRequest;
import com.viewmodels.resultviews.CreatePostResult;
import com.viewmodels.requestviews.CreatePostRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by archer on 2016-11-20.
 */
@Path("post")
public class PostEndpoint {

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreatePostResult createPost(CreatePostRequest postToCreate) {
        return new PostFacade().createPost(postToCreate);
    }
    // TODO: 23/11/16 ändra till en GET

    @POST
    @Path("getwall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WallResult getWall(WallRequest wallRequest) {
        return new PostFacade().getWall(wallRequest);
    }


    @GET
    @Path("getbyuser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PostView> byUser(@QueryParam("userid") String userid) {
        return new PostFacade().getPostsByuser(userid);
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public GetPostResult getPostById(@QueryParam("id") String postId) {
        return new PostFacade().getPostById(postId);
    }



}
