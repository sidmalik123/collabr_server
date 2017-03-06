package collabr;

import collabr.core.Match;
import collabr.core.Project;
import collabr.core.Skill;
import collabr.core.User;
import collabr.infra.InvalidEntityToResponseMapper;
import collabr.resources.*;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import collabr.guice_modules.ServerModule;
import collabr.infra.AuthenticationFilter;
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
        return "Collabr";
    }

    private final HibernateBundle<CollabrConfiguration> hibernateBundle
            = new HibernateBundle<CollabrConfiguration>(User.class, Project.class, Skill.class, Match.class){
        @Override
        public DataSourceFactory getDataSourceFactory(
                CollabrConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public void initialize(final Bootstrap<CollabrConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final CollabrConfiguration configuration, final Environment environment) {
        ServerModule serverModule = new ServerModule(hibernateBundle.getSessionFactory());
        Injector injector = Guice.createInjector(serverModule);
        configureResources(environment, injector);
        configureFilters(environment, injector);
        configureExceptionMappers(environment);
    }

    private void configureResources(final Environment environment, Injector injector){
        environment.jersey().register(injector.getInstance(BaseResource.class));
        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.jersey().register(injector.getInstance(ProjectResource.class));
        environment.jersey().register(injector.getInstance(SkillResource.class));
        environment.jersey().register(injector.getInstance(MatchResource.class));
    }

    private void configureFilters(final Environment environment, Injector injector){
        environment.jersey().register(injector.getInstance(AuthenticationFilter.class));
    }

    private void configureExceptionMappers(final Environment environment){
        environment.jersey().register(new InvalidEntityToResponseMapper());
    }

}
