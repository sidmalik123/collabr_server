package collabr.db;

import collabr.core.Match;
import collabr.core.Project;
import collabr.core.User;
import com.google.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class MatchDAO extends GenericDAO<Match> {

    @Inject
    public MatchDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Match find(User user, Project project){
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("user", user));
        criteria.add(Restrictions.eq("project", project));
        return uniqueResult(criteria);
    }
}
