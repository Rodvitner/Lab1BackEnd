package endpoint;

import viewmodels.CreatePostView;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by archer on 2016-11-20.
 */
@Path("post")
public class PostEndpoint {

    @POST
    public CreatePostView createPost(){

    }

}
