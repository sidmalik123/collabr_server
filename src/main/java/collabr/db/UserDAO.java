package collabr.db;

import collabr.core.User;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;


public class UserDAO extends AbstractDAO<User> {

    @Inject
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> findAll() {
        return list(namedQuery("com.mates.core.Users.findAll"));
    }

    public List<User> findByEmail(String email) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(email).append("%");
        return list(
                namedQuery("com.mates.core.Employee.findByEmail")
                        .setParameter("name", builder.toString())
        );
    }
}
