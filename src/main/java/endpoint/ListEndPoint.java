package endpoint;

import bo.ListFacade;
import viewmodels.requestviews.ListRequest;
import viewmodels.resultviews.GetUserResult;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
@Path("/user")
public class ListEndPoint {
    @GET
    @Path("listusers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<GetUserResult> listUsers(ListRequest listRequest) {
        return new ListFacade().listUsers(listRequest);
    }
}
