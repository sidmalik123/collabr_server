package collabr.services;


import collabr.core.Skill;
import collabr.core.User;
import collabr.db.SkillDAO;
import collabr.db.UserDAO;
import com.google.inject.Inject;
import org.joda.time.DateTime;

import javax.validation.Valid;
import java.util.List;

public class UserService {

    private UserDAO userDAO;
    private SkillDAO skillDAO;

    @Inject
    public UserService(UserDAO userDAO, SkillDAO skillDAO){
        this.userDAO = userDAO;
        this.skillDAO = skillDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public void createUser(@Valid User user){
        user.setCreatedAt(new DateTime());
        userDAO.saveOrUpdate(user);
    }

    public void addSkill(int userId, Skill skill) {
        User user = userDAO.findyId(userId);
        Skill skill1 = skillDAO.findByName(skill.getName());
        user.addSkill(skill1);
        userDAO.saveOrUpdate(user);
    }
}
