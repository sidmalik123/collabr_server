package guice_modules;

import collabr.CollabrConfiguration;
import collabr.core.User;
import collabr.db.UserDAO;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.ProvisionException;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;


public class ServerModule extends AbstractModule{
    private SessionFactory sessionFactory;

    public ServerModule(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void configure() {
//        bind(UserDAO.class).to(UserDAO.class);
    }

    @Provides
    SessionFactory provideSessionFactory(){
        if(sessionFactory == null){
            throw new ProvisionException("The Hibernate session factory has not yet been set");
        }
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

}
