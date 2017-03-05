package collabr.db;

import collabr.core.User;
import com.google.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class UserDAO extends GenericDAO<User> {

    @Inject
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findByEmail(String email) {
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("email", email));
        return uniqueResult(criteria);
    }
}
