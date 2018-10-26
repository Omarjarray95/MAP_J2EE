package dashboard.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	@Override
	public Resource getHigherSalary() {
		// TODO Auto-generated method stub
		Resource higherSalary = em.createQuery("")
		return null;
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
	public Integer countResourceDynamic(Integer idSeniority, Integer idCompetence, Integer idField, boolean available,
			boolean dayOff, boolean type) {
		// TODO Auto-generated method stub
		String and = " and ";
		String query = "SELECT count(r) FROM Resource r WHERE ";
		String seniority = "";
		
		if(idSeniority!=null)
			query += "r.seniority.idSeniority="+idSeniority + and;
		if(available==true)
			query += "r.availability="+Availability.Available.ordinal() + and;
		
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
