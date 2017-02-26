package collabr.services;


import collabr.core.User;
import collabr.db.UserDAO;
import com.google.inject.Inject;
import org.joda.time.DateTime;

import javax.validation.Valid;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public void createUser(@Valid User user){
        user.setCreatedAt(new DateTime());
        userDAO.saveOrUpdate(user);
    }
}
