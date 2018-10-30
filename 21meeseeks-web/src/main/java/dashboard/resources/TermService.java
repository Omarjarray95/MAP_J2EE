package dashboard.resources;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dashbord.interfaces.TermsBusinessInterface;

@Stateless
@Path("terms")
public class TermService {


	@EJB
	TermsBusinessInterface tbi;

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response countTerms(){
		return Response.status(Status.ACCEPTED).entity(tbi.countTerms()).build();
	}
	
	@GET
	@Path("income")
	@Produces(MediaType.APPLICATION_JSON)
	public Response termsIncome(@QueryParam(value="datefrom" )Date dateFrom , @QueryParam(value="dateto" ) Date dateTo){
		return Response.status(Status.ACCEPTED).entity(tbi.incomeInPeriod(dateFrom, dateTo)+"$").build();
	}
	@GET
	@Path("avgHourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AVGDays(@QueryParam(value="datefrom" )Date dateFrom , @QueryParam(value="dateto" ) Date dateTo){
		return Response.status(Status.ACCEPTED).entity(tbi.AVGTermDays(dateFrom, dateTo)).build();
	}
	@GET
	@Path("number")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberOfTerms(@QueryParam(value="datefrom" )Date dateFrom , @QueryParam(value="dateto" ) Date dateTo){
		return Response.status(Status.ACCEPTED).entity(tbi.countTermsInPeriod(dateFrom, dateTo)).build();
	}
	
	@GET
	@Path("competence/{competence}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response NumberOfTermsByCompetence(@PathParam(value="competence" )Integer id){
		return Response.status(Status.ACCEPTED).entity(tbi.countTermsByCompetence(id)).build();
	}
}
