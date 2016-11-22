package bo;

import exception.UserException;
import model.User;
import viewmodels.requestviews.BefriendRequest;
import viewmodels.requestviews.LoginRequest;
import viewmodels.resultviews.BefriendUserResult;
import viewmodels.requestviews.CreateUserRequest;
import viewmodels.resultviews.CreateUserResult;
import viewmodels.resultviews.LoginResult;

import javax.persistence.EntityManager;

/**
 * Created by simonlundstrom on 16/11/16.
 */
public class LoginRegisterFacade {
    private EntityManager prylchef;

    public LoginRegisterFacade() {
        prylchef = new LocalEntityManagerFactory().createEntityManager();
    }

    public CreateUserResult createUser(CreateUserRequest incoming) {
        CreateUserResult result = new CreateUserResult();
        try {
            new UserLogic(prylchef).registerUser(incoming.getName(),incoming.getEmail(),incoming.getPassword());
            result.setASuccess(true);
            result.setStatus("User created.");
        }
        catch(UserException ue) {
            result.setASuccess(false);
            result.setStatus(ue.getMessage());
        }
        finally {
            prylchef.close();
        }
        return result;
    }

    public LoginResult loginUser(LoginRequest loginRequest) {
        LoginResult res = new LoginResult();
        try {
            String uuid = new UserLogic(prylchef).loginUser(loginRequest.getEmail(),loginRequest.getPassword());
            res.setLogin(true);
            res.setReason("User logged in.");
            res.setUuid(uuid);
        }
        catch(UserException ue) {
            res.setLogin(false);
            res.setReason(ue.getMessage());
        }
        finally{
            prylchef.close();
        }
        return res;
    }

    /*
    // Deprecated? Wrong place?

    public CreateUserRequest getUserById(String email) {
        User userFromDB= new UserLogic(prylchef).getUser(email);
        if(userFromDB == null){
            userFromDB = new User();
        }
        prylchef.close();
        return new CreateUserRequest(userFromDB.getName(),userFromDB.getEmail(),null);

    }
     */

    private void abort() {
        // TODO: 2016-11-20 abort transaction in some smart way
    }

}
