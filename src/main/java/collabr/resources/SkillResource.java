package collabr.resources;

import collabr.aspect.Secured;
import collabr.core.Skill;
import collabr.infra.CollabrContext;
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
@Secured
public class SkillResource extends CollabrResource{

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

    @POST
    @Path("/add/user")
    @Timed
    @UnitOfWork
    public void addSkillForUser(@Valid Skill skill){
        CollabrContext collabrContext = getCollabrContext();
        skillService.addSkillForUser(collabrContext.getCurrentUserId(), skill);
    }


}
