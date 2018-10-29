package dashboard.business;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dashbord.interfaces.ResourceBusinessInterface;
import entities.Competence;
import entities.Level;
import entities.Resource;
import enums.Availability;

@Stateless
public class ResourceBusiness implements ResourceBusinessInterface{

	@PersistenceContext(name="21meeseeks-ejb")
	private EntityManager em;
	
	@Override
	public Integer countResource() {
		// TODO Auto-generated method stub
		int count = Math.toIntExact((Long)em.createQuery("SELECT count(r) FROM Resource r").getSingleResult());
		return count;
	}

	public Map<Resource,Long> monthlyMostTerm(){
		Calendar calendar = Calendar.getInstance();
		List<String> months = new DateFormatSymbols().getMonths();
		for()
	}
	
	
	@Override
	public Map<Resource,Long> getMostTerm(Integer limit,Date dateFrom , Date dateTo) {
		// TODO Auto-generated method stub
		Resource r = new Resource();
		Long count = new Long(0);
				
		//Split the Query into Conditions
		//explicit : => The DateStart AND DateEnd should be included
		//implicit : => The DateStart OR DateEnd should be included OR Bigger than DateStart AND DateEnd
		
		
		String query = "SELECT r , count(t.pkTerm.idResource) as numberOfTerms FROM Resource r , Term t "
					+ "WHERE t.pkTerm.idResource=r.idUser "
					+ " and " + 
					"t.dateStart BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
					+ " and " + 
					"t.dateEnd BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
					
					+ "GROUP BY t.pkTerm.idResource "
					+ "ORDER BY count(t) DESC";
	
		String date = " '"+ dateFrom.toString() +"' < t.dateStart < '"+dateTo.toString()+"' ";
		
		String hi = "hello '" + dateFrom.toString() + " s " + dateTo.toString();
		
		String and = " and ";
		
		String or = " OR ";
		
		String firstOne = "SELECT max(numberOfTerms) FROM ";
		
		System.out.println(query);
		Query mostTermsQuery = em.createQuery(query);
		
		if (limit!=null) {
			mostTermsQuery.setMaxResults(limit);
		}
		
		List<Object[]>  mostTerms = mostTermsQuery.getResultList();
		
		Map<Resource,Long> mostTermsMap= new HashMap<Resource, Long>();
		
		for (Object[] obj : mostTerms) {
			r = (Resource)obj[0];
			count = (Long)obj[1];
			System.out.println(" Resource :  "+ r +" count : " + count);
			mostTermsMap.put(r, count);
		}
		return mostTermsMap;
	}

	@Override
	public Integer countResourceBySeniority(Integer idSeniority) {
		// TODO Auto-generated method stub
		int seniority = idSeniority;
		System.out.println(seniority);
		int count = Math.toIntExact((Long)em.createQuery("SELECT count(r) FROM Resource r WHERE r.seniority.idSeniority=:idSeniority").setParameter("idSeniority", idSeniority).getSingleResult());
		System.out.println(count);
		return count;
	}

	@Override
	public Integer countResourceFreelancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourceLevio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourceAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourceInDayOff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourceByField(Integer idField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourceByCompetence(Integer idCompetence) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourceDynamic(Integer idSeniority, Integer idCompetence, Integer idField, String available,
			boolean dayOff, boolean type,Integer level) {
		// TODO Auto-generated method stub
		String and = " and ";
		String query = "SELECT count(r) FROM Resource r , Level l WHERE ";
		String seniority = "";
		
		System.out.println(idSeniority);
		System.out.println(available);
		//System.out.println(Availability.valueOf(available));
				
		if(idSeniority!=null)
			query += "r.seniority.idSeniority="+idSeniority + and;
		if(available!=null)
			query += "r.availability='"+available +"'" +and;
		if(idCompetence!=null){
			query += "l.pkLevel.idCompetence="+idCompetence+"" +and+"l.pkLevel.idResource=r.idUser" + and;
			if (level!=null) {
				query+="l.level >= " + level + and;
			}
		}
		
		if (query.endsWith(and)) 
			query = query.substring(0, query.lastIndexOf(and));
		
		if (query.endsWith("WHERE ")) 
			query = query.substring(0, query.lastIndexOf("WHERE"));
		
		System.out.println(query);
		
		System.out.println(Availability.Available);
		
		Integer count = Math.toIntExact((Long)em.createQuery(query).getSingleResult());
		
		return count;
	}
	
	//Calculate Number of Days between too Dates without counting the weekends
	
	private long countWeekDaysStream ( LocalDate start , LocalDate stop ) {
	    // Code taken from the Answer by Ravindra Ranwala.
	    // https://stackoverflow.com/a/51010738/642706
	    long count = 0;
	    Set < DayOfWeek > weekend = EnumSet.of( DayOfWeek.SATURDAY , DayOfWeek.SUNDAY );
	    final long weekDaysBetween =  getDays(start, start.until(stop, ChronoUnit.DAYS))
	    		.filter( d -> ! weekend.contains( ((LocalDate)d).getDayOfWeek() ) ).count();
	    		//start.dates until(stop, ChronoUnit.DAYS)
	              //                       .filter( d -> ! weekend.contains( d.getDayOfWeek() ) )
	                //                     .count();
	    return count;
	}
	public static Stream<LocalDate> getDays(LocalDate start, long days)
    {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(days+1);
    }

	@Override
	public Map<Resource, Integer> getResourcesByAdress(String adress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Competence, Long> getRessourcesByCompetence() {
		// TODO Auto-generated method stub
		List<Object[]> listResource = em.createQuery("SELECT c , count(l.pkLevel.idResource) FROM Level l , Competence c "
				+ "WHERE c.idCompetence = l.pkLevel.idCompetence GROUP BY l.pkLevel.idCompetence").getResultList();
		Competence c = new Competence();
		Map<Competence, Long> resultMap = new HashMap<Competence, Long>();
		for(Object[] obj : listResource){
			
		//	Competence c1 = (Competence)em.find(Competence.class, obj[0]);
			//System.out.println("com : " + c1);
			System.out.println("obj[0]: "+obj[0]);
			//Competence c = (Competence)em.createQuery("SELECT c FROM Competence c WHERE c.idCompetence = :comp").setParameter("comp", obj[0]).getSingleResult();
			Long count = (Long)obj[1];
			//System.out.println(c.getLabel());
			System.out.println(count);
			c=(Competence)obj[0];
			resultMap.put(c, count);
		}
		return resultMap;
	}

	@Override
	public Competence getCompetence() {
		return (Competence)em.createQuery("SELECT c FROM Competence c ").getResultList().get(0);
	}
	
}
