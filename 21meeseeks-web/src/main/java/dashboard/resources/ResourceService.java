package dashboard.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dashbord.interfaces.ResourceBusinessInterface;

@Stateless
@Path("test")
public class ResourceService {

	@EJB
	ResourceBusinessInterface rbi;
	
	@GET
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Response countResource(){
		return Response.status(Status.ACCEPTED).entity(rbi.countResource()).build();
	}
	
}
