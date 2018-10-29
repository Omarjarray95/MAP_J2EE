package dashboard.resources;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import dashbord.interfaces.ResourceBusinessInterface;
import entities.Competence;
import entities.Resource;
import utilities.Secured;

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
	public Response countResourceDynamic(
			@QueryParam(value="seniority") Integer seniority,
			@QueryParam(value="availability") String availability,
			@QueryParam(value="competence") Integer idCompetence,
			@QueryParam(value="level") Integer level
			){
		//To Be Continued 
		//Complete the Field and the other parameters
		//Test the case where more than one competence is required
		
		if (level!=null && idCompetence == null)
			return Response.status(Status.BAD_REQUEST).entity("Level without Competence").build();
		double percentage = (double)rbi.countResourceDynamic(seniority, idCompetence, null, availability , false , false,level)/rbi.countResource()*100;
		System.out.println(percentage);
		System.out.println(rbi.countResourceDynamic(seniority, idCompetence, null, availability , false , false,level));
		System.out.println((double)rbi.countResourceDynamic(seniority, idCompetence, null, availability , false ,false, level)/rbi.countResource());
		return Response.status(Status.ACCEPTED).entity(percentage+"%").build();
	}
	
	@GET
	@Path("competence/resource")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompetenceByResource(){
		Map<Competence, Long> map = rbi.getRessourcesByCompetence();
	    GenericEntity<Map<Competence,Long>> entity = new GenericEntity<Map<Competence,Long>>(map) {};
	    List<Competence> listC = map.keySet().stream().collect(Collectors.toList());
	    List<List<Object>> listO = new ArrayList<>();
	    for(Map.Entry<Competence, Long> e : map.entrySet())
	    {
	    	List <Object> list = new ArrayList<>();
	    	list.add((Competence)e.getKey());
	    	list.add((Long)e.getValue());
	    	listO.add(list);
	    }
	    return Response.status(Status.ACCEPTED).entity(listO).build();
	}
	
	@GET
	@Path("competence/xxx/aaa")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompetence(){
		return Response.status(Status.ACCEPTED).entity(rbi.getCompetence()).build();
	}
	
}
