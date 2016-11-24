package bo;

import bo.translators.UserUserViewMapper;
import viewmodels.requestviews.ListUsersRequest;
import viewmodels.generalviews.UserView;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class ListFacade {
    EntityManager prylchef;

    public ListFacade() { prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public List<UserView> listUsers(ListUsersRequest listUsersRequest) {
        List<UserView> res = new UserUserViewMapper()
                .translateListOfA(new UserLogic(prylchef)
                                 .listUsersbyName(listUsersRequest.getQuery(),
                                                  listUsersRequest.getStartAt(),
                                                  listUsersRequest.getAmountOf())
                                );
        prylchef.close();
        return res;
    }
}
