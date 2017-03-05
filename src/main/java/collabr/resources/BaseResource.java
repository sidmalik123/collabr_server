package collabr.resources;

import collabr.core.User;
import collabr.core.UserCredentials;
import collabr.services.UserService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@PermitAll
public class BaseResource {

    private UserService userService;

    @Inject
    public BaseResource(UserService userService){
        this.userService = userService;
    }

    @POST
    @Path("create")
    @Timed
    @UnitOfWork
    @PermitAll
    public void createUser(@Valid User user){
        userService.createUser(user);
    }

    @PermitAll
    @POST
    @Timed
    @UnitOfWork
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public String loginUser(UserCredentials userCredentials){
        String authToken = userService.loginUser(userCredentials.getEmail(), userCredentials.getPassword());
        return authToken;
    }
}
