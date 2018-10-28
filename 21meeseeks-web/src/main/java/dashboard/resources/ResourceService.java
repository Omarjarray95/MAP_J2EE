package dashboard.resources;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import dashbord.interfaces.ResourceBusinessInterface;
import entities.Resource;

@Stateless
@Path("resources")
public class ResourceService {

	@EJB
	ResourceBusinessInterface rbi;
		
	@GET
	@Path("resource/mostTerm")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMostTerm(@DefaultValue("10") @QueryParam(value = "limit") Integer limit , @QueryParam(value = "highest") Boolean highest
			, @QueryParam(value = "datefrom") String dateFrom , @QueryParam(value = "dateto") String dateTo){
		
		//In case the user enter a value for 'highest' different to true or false , automatically it will be false
		
		Date dateFromDate = null;
		Date dateToDate = null ;
		
		if (dateFrom!=null || dateTo!=null) {
			if (dateTo==null){
				dateToDate = new Date();
				dateFromDate = new Date(dateFrom);
			}else if (dateFrom == null){
				dateToDate = new Date(dateTo);
				dateFromDate = new Date(0);
			}else{
				dateFromDate = new Date(dateFrom);
				dateToDate = new Date(dateTo);
			}
			
		}
		
		if (highest != null &&highest==true) {
			List<Long> max = (List)rbi.getMostTerm(null,dateFromDate,dateToDate).values().stream().collect(Collectors.toList());
			Long maxNum = max.stream().max(Comparator.comparing(Long::valueOf)).get();
			Map<Resource, Long> HighestTermsNumber = ((Map<Resource, Long>) rbi.getMostTerm(null,dateFromDate,dateToDate)).entrySet().
					stream().filter(e->e.getValue()==maxNum)
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			return Response.status(Status.ACCEPTED).entity(HighestTermsNumber).build();
		}
		return Response.status(Status.ACCEPTED).entity(rbi.getMostTerm(limit,dateFromDate,dateToDate)).build();
	}
	
	
	//Count Number of Resources
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
	public Response countResourceDynamic(@QueryParam(value="seniority") Integer seniority,@QueryParam(value="availability") String availability){
		double percentage = (double)rbi.countResourceDynamic(seniority, null, null, availability , false , false)/rbi.countResource()*100;
		System.out.println(percentage);
		System.out.println((double)rbi.countResourceDynamic(seniority, null, null, availability , false , false)/rbi.countResource());
		return Response.status(Status.ACCEPTED).entity(percentage+"%").build();
	}
	
}
