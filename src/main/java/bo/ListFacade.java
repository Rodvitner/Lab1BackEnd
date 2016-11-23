package bo;

import bo.translators.UserUserViewMapper;
import model.User;
import viewmodels.requestviews.ListRequest;
import viewmodels.resultviews.GetUserResult;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class ListFacade {
    EntityManager prylchef;

    public ListFacade() { prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public List<GetUserResult> listUsers(ListRequest listRequest) {
        return new UserUserViewMapper().translateListOfA(new UserLogic(prylchef)
                .listUsersbyName(listRequest.getQuery(),
                                 listRequest.getStartAt(),
                                 listRequest.getAmountOf())
                                );
    }
}
