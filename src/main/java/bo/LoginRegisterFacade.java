package bo;

import exception.UserException;
import viewmodels.requestviews.LoginRequest;
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
        CreateUserResult result;
        try {
            new UserLogic(prylchef).registerUser(incoming.getName(),incoming.getEmail(),incoming.getPassword());
            result = new CreateUserResult(true,"user created");
        }
        catch(UserException ue) {
            result = new CreateUserResult(false,ue.getMessage());
        }
        finally {
            prylchef.close();
        }
        return result;
    }

    public LoginResult loginUser(LoginRequest loginRequest) {
        LoginResult res;
        try {
            String uuid = new UserLogic(prylchef).loginUser(loginRequest.getEmail(),loginRequest.getPassword());
            res = new LoginResult(true,"User logged in.",uuid);
        }
        catch(UserException ue) {
            res = new LoginResult(false,ue.getMessage(),null);
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
