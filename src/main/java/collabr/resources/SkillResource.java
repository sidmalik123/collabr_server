package collabr.resources;

import collabr.core.Skill;
import collabr.services.SkillService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/skills")
@Produces(MediaType.APPLICATION_JSON)
public class SkillResource {

    private SkillService skillService;

    @Inject
    public SkillResource(SkillService skillService){
        this.skillService = skillService;
    }

    @POST
    @Path("/add")
    @Timed
    @UnitOfWork
    public void addSkill(@Valid Skill skill){
        skillService.addSkill(skill);
    }
}
