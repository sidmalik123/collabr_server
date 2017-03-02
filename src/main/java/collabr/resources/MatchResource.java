package collabr.resources;

import collabr.api.MatchDTO;
import collabr.core.Match;
import collabr.services.MatchService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/match")
@Produces(MediaType.APPLICATION_JSON)
public class MatchResource {

    private MatchService matchService;

    @Inject
    public MatchResource(MatchService matchService){
        this.matchService = matchService;
    }

    @POST
    @Timed
    @UnitOfWork
    public void addMatch(MatchDTO matchDTO){
        matchService.addMatch(matchDTO.getUserId(), matchDTO.getProjectId());
    }
}
