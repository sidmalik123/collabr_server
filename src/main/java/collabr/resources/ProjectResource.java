package collabr.resources;

import collabr.core.Project;
import collabr.core.User;
import collabr.db.UserDAO;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("/projects")
//@Produces(MediaType.APPLICATION_JSON)
//public class ProjectResource {
//
//
//
//}
