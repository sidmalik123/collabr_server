package collabr.services;

import collabr.core.Match;
import collabr.core.Project;
import collabr.core.User;
import collabr.db.MatchDAO;
import collabr.db.ProjectDAO;
import collabr.db.UserDAO;
import com.google.inject.Inject;

public class MatchService {

    private MatchDAO matchDAO;
    private UserDAO userDAO;
    private ProjectDAO projectDAO;

    @Inject
    public MatchService(MatchDAO matchDAO, UserDAO userDAO, ProjectDAO projectDAO){
        this.matchDAO = matchDAO;
        this.userDAO = userDAO;
        this.projectDAO = projectDAO;
    }

    public void addMatch(int userId, int projectId){
        User user = userDAO.findyId(userId);
        Project project = projectDAO.findyId(projectId);

        Match existingMatch = matchDAO.find(user, project);
        if (existingMatch != null){
            existingMatch.setMutual(true);
            matchDAO.saveOrUpdate(existingMatch);
            return;
        }
        // else this is a new match
        Match newMatch = new Match(user, project, false);
        matchDAO.saveOrUpdate(newMatch);
    }
}
