package collabr;

import collabr.core.User;
import collabr.db.UserDAO;
import collabr.resources.AdminResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;
import guice_modules.ServerModule;
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
            = new HibernateBundle<CollabrConfiguration>(User.class){
        @Override
        public DataSourceFactory getDataSourceFactory(
                CollabrConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

    private GuiceBundle<CollabrConfiguration> guiceBundle;

    @Override
    public void initialize(final Bootstrap<CollabrConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        guiceBundle = GuiceBundle.<CollabrConfiguration>newBuilder()
                .addModule(new ServerModule(hibernateBundle.getSessionFactory()))
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(CollabrConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final CollabrConfiguration configuration, final Environment environment) {


        Injector injector = guiceBundle.getInjector();
        final AdminResource resource = injector.getInstance(AdminResource.class);
        environment.jersey().register(resource);
    }

}
