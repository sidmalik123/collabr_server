package collabr.resources;

import collabr.core.User;
import collabr.db.UserDAO;
import collabr.services.UserService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserService userService;

    @Inject
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GET
    @Path("/users")
    @Timed
    @UnitOfWork
    public List<User> returnAllUsers(){
        return userService.getAllUsers();
    }

    @POST
    @Path("create/user")
    @Timed
    @UnitOfWork
    public void createUser(@Valid User user){
        userService.createUser(user);
    }
}
