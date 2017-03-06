package collabr.infra;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidEntityToResponseMapper implements ExceptionMapper<InvalidEntityException> {
    private StatusType status;

    @Override
    public Response toResponse(InvalidEntityException e) {
        status = Status.BAD_REQUEST;
        return Response.status(status).entity(e.getMessage()).type("text/plain").build();
    }
}
