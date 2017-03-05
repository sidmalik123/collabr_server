package collabr.infra;

import javax.security.auth.Subject;
import java.security.Principal;

public class CollabrContext implements Principal{
    int currentUserId;

    public CollabrContext(){}

    public CollabrContext(int currentUserId){
        this.currentUserId = currentUserId;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }


    /*
        these methods have not being used
     */
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
