package dashboard.business;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dashbord.interfaces.ResourceBusinessInterface;
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
				
		String query = "SELECT r , count(t.pkTerm.idResource) as numberOfTerms FROM Resource r , Term t "
					+ "WHERE t.pkTerm.idResource=r.idUser "
				/*	+ " and " + 
					"t.dateStart BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
					+ " and " + 
					"t.dateEnd BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
					*/
					+ "GROUP BY t.pkTerm.idResource "
					+ "ORDER BY count(t) DESC";
	
	//	String date = " '"+ dateFrom.toString() +"' < t.dateStart < '"+dateTo.toString()+"' ";
		
	//	String hi = "hello '" + dateFrom.toString() + " s " + dateTo.toString();
		
		String and = " and ";
		
		String firstOne = "SELECT max(numberOfTerms) FROM ";
		
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
			boolean dayOff, boolean type) {
		// TODO Auto-generated method stub
		String and = " and ";
		String query = "SELECT count(r) FROM Resource r WHERE ";
		String seniority = "";
		
		System.out.println(idSeniority);
		System.out.println(available);
		//System.out.println(Availability.valueOf(available));
				
		if(idSeniority!=null)
			query += "r.seniority.idSeniority="+idSeniority + and;
		if(available!=null)
			query += "r.availability='"+available +"'" +and;
		if(idCompetence!=null){
			query += "r.availability='"+idCompetence+"'" +and;
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

}
