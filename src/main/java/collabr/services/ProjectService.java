package collabr.services;

import collabr.core.Project;
import collabr.core.User;
import collabr.db.ProjectDAO;
import collabr.db.UserDAO;
import com.google.inject.Inject;

import java.util.List;

public class ProjectService {

    private UserDAO userDAO;
    private ProjectDAO projectDAO;

    @Inject
    public ProjectService(UserDAO userDAO, ProjectDAO projectDAO){
        this.userDAO = userDAO;
        this.projectDAO = projectDAO;
    }

    public List<Project> getProjects(int userId){
        User user = userDAO.findyId(userId);
        return user.getProjects();
    }

    public void createProject(int userId, Project project){
        User user = userDAO.findyId(userId);
        project.setUser(user);
        projectDAO.saveOrUpdate(project);
    }
}
