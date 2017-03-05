package collabr.resources;

import collabr.infra.CollabrContext;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

public class CollabrResource {
    @Context
    private SecurityContext securityContext;

    protected CollabrContext getCollabrContext(){
        return (CollabrContext) securityContext.getUserPrincipal();
    }

}
