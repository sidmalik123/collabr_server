package collabr.resources;

import collabr.aspect.Secured;
import collabr.core.Skill;
import collabr.core.User;
import collabr.core.UserCredentials;
import collabr.services.UserService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Secured
public class UserResource extends CollabrResource{

    private UserService userService;

    @Inject
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<User> returnAllUsers(){
        return userService.getAllUsers();
    }

}
