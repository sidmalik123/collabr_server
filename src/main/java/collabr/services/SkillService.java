package collabr.services;

import collabr.core.Skill;
import collabr.db.SkillDAO;
import com.google.inject.Inject;

public class SkillService {

    private SkillDAO skillDAO;

    @Inject
    public SkillService(SkillDAO skillDAO){
        this.skillDAO = skillDAO;
    }

    public void addSkill(Skill skill){
        skillDAO.saveOrUpdate(skill);
    }
}
