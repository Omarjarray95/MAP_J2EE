package dashboard.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import dashbord.interfaces.ResourceBusinessInterface;

@Stateless
@Path("test")
public class ResourceService {

	@EJB
	ResourceBusinessInterface rbi;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response countResource(){
		return Response.status(Status.ACCEPTED).entity(rbi.countResource()).build();
	}
	
	@GET
	@Path("{seniority}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response countResourceBySeniority(@PathParam(value="seniority") int seniority){
		return Response.status(Status.ACCEPTED).entity(rbi.countResourceBySeniority(seniority)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response countResourceDynamic(@QueryParam(value="seniority") int seniority,@QueryParam(value="availability") int availability){
		return Response.status(Status.ACCEPTED).entity(rbi.countResourceDynamic(seniority, null, null, true , false , false)).build();
	}
	
}
