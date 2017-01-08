package com.bo;

import com.exception.UserException;
import com.viewmodels.requestviews.LoginRequest;
import com.viewmodels.requestviews.CreateUserRequest;
import com.viewmodels.resultviews.CreateUserResult;
import com.viewmodels.resultviews.LoginResult;
import com.viewmodels.resultviews.Result;

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

    public Result logoutUser(String userToLogout) {
        Result res = null;
        try {
            new UserLogic(prylchef).logoutUser(userToLogout);
            res = new Result(true,"User logged out.");
        }catch(UserException ue) {
            res = new Result(false,"Could not log out user.");
        }finally{
            prylchef.close();
        }
        return res;
    }

    private void abort() {
        // TODO: 2016-11-20 abort transaction in some smart way
    }
}
