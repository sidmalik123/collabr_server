package collabr.infra;

import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
abstract public class ExceptionToResponseMapper<E extends Throwable> implements ExceptionMapper<E> {
    private int status;

    @Override
    public Response toResponse(E e) {
        if(e instanceof InvalidEntityException) status = HttpStatus.UNPROCESSABLE_ENTITY_422;
        return Response.status(status).entity(e.getMessage()).type("text/plain").build();
    }
}
