package endpoint;

import bo.PostFacade;
import viewmodels.resultviews.WallResult;
import viewmodels.requestviews.WallRequest;
import viewmodels.resultviews.CreatePostResult;
import viewmodels.requestviews.CreatePostRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by archer on 2016-11-20.
 */
@Path("post")
public class PostEndpoint {

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreatePostResult createPost(CreatePostRequest postToCreate){
        return new PostFacade().createPost(postToCreate);
    }

    @POST
    @Path("getwall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WallResult getWall(WallRequest wallRequest) {
        return new PostFacade().getWall(wallRequest);
    }

}
