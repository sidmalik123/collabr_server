package collabr.resources;

import collabr.core.Project;
import collabr.core.User;
import collabr.db.UserDAO;
import collabr.services.ProjectService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource extends CollabrResource {

    private ProjectService projectService;

    @Inject
    public ProjectResource(ProjectService projectService){
        this.projectService = projectService;
    }

    @GET
    @Path("/{userid}")
    @Timed
    @UnitOfWork
    public List<Project> getUserProjects(@PathParam("userid") int userId){
        return projectService.getProjects(userId);
    }

    @POST
    @Path("/{userid}")
    @Timed
    @UnitOfWork
    public void createProject(@PathParam("userid") int userId, @Valid Project project){
        projectService.createProject(userId, project);
    }


}
