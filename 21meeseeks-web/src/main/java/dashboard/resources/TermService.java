package dashboard.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dashbord.interfaces.TermsBusinessInterface;

@Stateless
@Path("terms")
public class TermService {


	@EJB
	TermsBusinessInterface rbi;

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response countTerms(){
		return Response.status(Status.ACCEPTED).entity(rbi.countTerms()).build();
	}
	
}
