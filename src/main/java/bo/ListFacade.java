package bo;

import bo.translators.UserUserViewMapper;
import viewmodels.requestviews.ListUsersRequest;
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

    public List<GetUserResult> listUsers(ListUsersRequest listUsersRequest) {
        List<GetUserResult> res = new UserUserViewMapper()
                .translateListOfA(new UserLogic(prylchef)
                                 .listUsersbyName(listUsersRequest.getQuery(),
                                                  listUsersRequest.getStartAt(),
                                                  listUsersRequest.getAmountOf())
                                );
        prylchef.close();
        return res;
    }
}
