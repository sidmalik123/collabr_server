package collabr.db;

import collabr.core.Project;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

public class ProjectDAO extends GenericDAO<Project> {

    @Inject
    public ProjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
