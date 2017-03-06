package collabr.infra;

import javax.ws.rs.WebApplicationException;

public class InvalidEntityException extends WebApplicationException {

    public InvalidEntityException(String message){
        super(message);
    }
}
