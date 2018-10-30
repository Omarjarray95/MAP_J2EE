package dashboard.business;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dashbord.interfaces.TermsBusinessInterface;
import entities.Term;

@Stateless
public class TermsBusiness implements TermsBusinessInterface{


	@PersistenceContext(name="21meeseeks-ejb")
	private EntityManager em;

	@Override
	public Integer countTerms() {
		// TODO Auto-generated method stub
		Integer count = (Integer)em.createQuery("SELECT count(t) FROM Term t").getSingleResult();
		return count;
	}

	@Override
	public Integer countTermsInPeriod(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		if (dateFrom==null)
			dateFrom = new Date(0);
		if (dateTo==null)
			dateTo= new Date();
		
		String query2 = "SELECT count(t) FROM Term t WHERE "
				+ "t.dateStart BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'"
				+" and "+ "t.dateEnd BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'";
		Integer count = Math.toIntExact((Long)em.createQuery(query2).getSingleResult());
		return count;
	}

	@Override
	public Integer countTermsByCompetence(Integer idCompetence) {
		// TODO Auto-generated method stub
		System.out.println(idCompetence);
		String query = "SELECT count(t) FROM Term t JOIN t.resources r LEFT JOIN r.levels l WHERE l.pkLevel.idCompetence="+idCompetence;
		Integer count = Math.toIntExact((Long)em.createQuery(query).getSingleResult());
		System.out.println(count);
		return count;
	}

	@Override
	public Integer countTermsByProject(Integer project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float countTermsFreelanceVSEmployee() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float totalIncome() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float monthlyIncomeBy(LocalDate year) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double AVGTermDays(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		if (dateFrom==null)
			dateFrom = new Date(0);
		if (dateTo==null)
			dateTo= new Date();
		
		String query = "SELECT SUM(date(t.dateEnd)-date(t.dateStart)) FROM Term t WHERE "
		+ "t.dateStart BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'"
		+" and "+ "t.dateEnd BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'";
		
		Double sum = (Double)em.createQuery(query).getSingleResult();
		
		String query2 = "SELECT t FROM Term t WHERE "
				+ "t.dateStart BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'"
				+" and "+ "t.dateEnd BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'";
		List<Term> listTerm = em.createQuery(query2).getResultList();		
		System.out.println(sum);
		Double sum2=new Double(0);
		for (Term term : listTerm) {
			sum2+=countWeekDaysStream(term.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					,term.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		System.out.println("SUM 2 : "+sum2);
		return sum2; 
	}
	@Override
	public Double incomeInPeriod(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		if (dateFrom==null)
			dateFrom = new Date(0);
		if (dateTo==null)
			dateTo= new Date();
		
		String query ="SELECT AVG(t.dailyFee) FROM Term t WHERE "
				+ "t.dateStart BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'"
		+" and "+ "t.dateEnd BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateTo)+"'";
		Double avg = (Double)em.createQuery(query).getSingleResult();
		return avg*AVGTermDays(dateFrom, dateTo);
	}

	@Override
	public List<Term> getTermsByResource(Integer id, Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		if (dateFrom==null)
			dateFrom = new Date(0);
		if (dateTo==null)
			dateTo= new Date();
		
		List<Term> listTerm = em.createQuery("Select t from Term t where t.pkTerm.idResource = :id"
		/*		 + "t.dateStart BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
				+ " and " + 
				"t.dateEnd BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
			*/	).setParameter("id", id).getResultList();
		return listTerm;
	}

	//Calculate Number of Days between too Dates without counting the weekends
	
		private long countWeekDaysStream ( LocalDate start , LocalDate stop ) {
		    // Code taken from the Answer by Ravindra Ranwala.
		    // https://stackoverflow.com/a/51010738/642706
		    
			if (start.isAfter(stop)) {
			LocalDate inter=start;
			start=stop;
			stop=start;
			}
			long count = 0;
		    Set < DayOfWeek > weekend = EnumSet.of( DayOfWeek.SATURDAY , DayOfWeek.SUNDAY );
		    final long weekDaysBetween =  getDays(start, start.until(stop, ChronoUnit.DAYS))
		    		.filter( d -> ! weekend.contains( ((LocalDate)d).getDayOfWeek() ) ).count();
		    		//start.datesuntil(stop, ChronoUnit.DAYS)
		              //                       .filter( d -> ! weekend.contains( d.getDayOfWeek() ) )
		                //                     .count();
		    return weekDaysBetween;
		}
		

		public static Stream<LocalDate> getDays(LocalDate start, long days){
	        return Stream.iterate(start, date -> date.plusDays(1))
	                .limit(days+1);
	    }
}
