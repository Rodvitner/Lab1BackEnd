package endpoint;

import bo.LocalEntityManagerFactory;
import bo.ProfileFacade;
import viewmodels.resultviews.CreatePostResult;
import viewmodels.requestViews.CreatePostView;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public CreatePostResult createPost(CreatePostView postToCreate){
        return new ProfileFacade(new LocalEntityManagerFactory().createEntityManager()).createPost(postToCreate);

    }

}
