package collabr.services;


import collabr.core.Skill;
import collabr.core.User;
import collabr.db.SkillDAO;
import collabr.db.UserDAO;
import com.google.inject.Inject;
import infra.security.CollabrSecurity;
import org.joda.time.DateTime;

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

    public void createUser(User user){
        user.setCreatedAt(new DateTime());
        String hashedPassword = CollabrSecurity.hash(user.getHashPassword());
        user.setHashPassword(hashedPassword);
        userDAO.saveOrUpdate(user);
    }

    public void addSkill(int userId, Skill skill) {
        User user = userDAO.findyId(userId);
        Skill skill1 = skillDAO.findByName(skill.getName());
        user.addSkill(skill1);
        userDAO.saveOrUpdate(user);
    }

    public String loginUser(String email, String password) {
        String hashedPassword = CollabrSecurity.hash(password);
        User user = userDAO.findByEmail(email);
        if (user == null ){

        }
        if (!user.getHashPassword().equals(hashedPassword)){
            // invalid password
        }

        // issue a token
        String token = null;
        try {
            token = CollabrSecurity.encrypt(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }


}
