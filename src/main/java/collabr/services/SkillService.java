package collabr.services;

import collabr.core.Skill;
import collabr.core.User;
import collabr.db.SkillDAO;
import collabr.db.UserDAO;
import com.google.inject.Inject;

public class SkillService {

    private SkillDAO skillDAO;
    private UserDAO userDAO;

    @Inject
    public SkillService(SkillDAO skillDAO, UserDAO userDAO){

        this.skillDAO = skillDAO;
        this.userDAO = userDAO;
    }

    public void addSkill(Skill skill){
        skillDAO.saveOrUpdate(skill);
    }

    public void addSkillForUser(int userId, Skill skill) {
        User user = userDAO.findyId(userId);
        user.addSkill(skill);
    }
}
