package collabr.db;

import collabr.core.Skill;
import com.google.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class SkillDAO extends GenericDAO<Skill> {

    @Inject
    public SkillDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Skill findByName(String name){
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("name", name));
        return uniqueResult(criteria);
    }
}
