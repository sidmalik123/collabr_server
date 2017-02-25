package collabr.resources;

import collabr.core.User;
import collabr.db.UserDAO;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sidmalik on 2017-02-14.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
    private UserDAO userDAO;

    public AdminResource(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GET
    @Timed
    public Map<String, String> sayHello() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Greeting", "Good Evening!");
        return map;
    }

    @GET
    @Path("/users")
    @Timed
    @UnitOfWork
    public List<User> returnAllUsers(){
        return userDAO.findAll();
    }
}
