package collabr.infra;

import collabr.aspect.Secured;
import collabr.core.User;
import collabr.db.UserDAO;
import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private UserDAO userDAO;
    private SessionFactory sessionFactory;

    @Inject
    public AuthenticationFilter(UserDAO userDAO, SessionFactory sessionFactory){
        this.userDAO = userDAO;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic")) {
            throw new NotAuthorizedException("Not authorized");

        }
        String token = authorizationHeader.substring("Basic".length()).trim();

        validateToken(token, requestContext);

    }

    private void validateToken(String token, ContainerRequestContext requestContext) {
        try {
            String decryptedToken = CollabrSecurity.decrypt(token);
            Session session = sessionFactory.openSession();
            ManagedSessionContext.bind(session);
            User user = userDAO.findByEmail(decryptedToken);
            session.close();
            if (user != null){
                CollabrContext collabrContext = new CollabrContext(user.getId());
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return collabrContext;
                    }

                    @Override
                    public boolean isUserInRole(String s) {
                        return false;
                    }

                    @Override
                    public boolean isSecure() {
                        return false;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return null;
                    }
                });
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new NotAuthorizedException("token validation failed");
    }
}
