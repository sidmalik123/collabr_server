package collabr.resources;

import collabr.aspect.Secured;
import collabr.core.Skill;
import collabr.core.User;
import collabr.core.UserCredentials;
import collabr.services.UserService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import infra.security.CollabrContext;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends CollabrResource{

    private UserService userService;

    @Context
    private SecurityContext securityContext;

    protected CollabrContext getCollabrContext(SecurityContext securityContext){
        return (CollabrContext) securityContext;
    }

    @Inject
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GET
    @Timed
    @UnitOfWork
    @Secured
    public List<User> returnAllUsers(){

        System.out.println(getCollabrContext(securityContext).getCurrentUserId());
        return userService.getAllUsers();
    }

    @POST
    @Path("create")
    @Timed
    @UnitOfWork
    @PermitAll
    public void createUser(@Valid User user){
        userService.createUser(user);
    }

    @POST
    @Path("/add/skill")
    @Timed
    @UnitOfWork
    public void createUser(@Valid Skill skill){
        userService.addSkill(1, skill);
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
