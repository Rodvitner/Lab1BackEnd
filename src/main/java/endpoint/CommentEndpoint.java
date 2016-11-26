package endpoint;

import bo.CommentFacade;
import model.Comment;
import viewmodels.generalviews.CommentView;
import viewmodels.requestviews.CreateCommentRequest;
import viewmodels.resultviews.CommentListResult;
import viewmodels.resultviews.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public CommentListResult getComments(@QueryParam("PostId") int postId) {
        return new CommentFacade().getCommentsByPostId(postId);
    }
}
