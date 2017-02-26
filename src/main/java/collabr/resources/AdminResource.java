package collabr.resources;

import collabr.core.User;
import collabr.db.UserDAO;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
    private UserDAO userDAO;

    @Inject
    public AdminResource(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GET
    @Path("/users")
    @Timed
    @UnitOfWork
    public List<User> returnAllUsers(){
        return userDAO.findAll();
    }

    @POST
    @Path("create/user")
    @Timed
    @UnitOfWork
    public void createUser(@Valid User user){
        userDAO.saveOrUpdate(user);
    }
}
