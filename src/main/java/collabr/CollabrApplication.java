package collabr;

import collabr.core.User;
import collabr.db.UserDAO;
import collabr.resources.AdminResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CollabrApplication extends Application<CollabrConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CollabrApplication().run(args);
    }

    @Override
    public String getName() {
        return "Mates";
    }

    private final HibernateBundle<CollabrConfiguration> hibernateBundle
            = new HibernateBundle<CollabrConfiguration>(
            User.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                CollabrConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(
            final Bootstrap<CollabrConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final CollabrConfiguration configuration,
                    final Environment environment) {
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        final AdminResource resource = new AdminResource(userDAO);
        environment.jersey().register(resource);
    }

}
