package endpoint;

import bo.CommentFacade;
import viewmodels.requestviews.CreateCommentRequest;
import viewmodels.resultviews.Result;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by simonlundstrom on 23/11/16.
 */
@Path("/comment")
public class CommentEndpoint {
    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result postComment(CreateCommentRequest ccr) {
        return new CommentFacade().postComment(ccr);
    }
}
