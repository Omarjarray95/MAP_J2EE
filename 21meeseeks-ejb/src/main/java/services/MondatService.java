package services;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PersistenceContext;

import entities.Competence;
import entities.Resource;
import entities.Term;
import enums.Availability;
import interfaces.MondatServiceLocal;
import interfaces.MondatServiceRemote;

/**
 * Session Bean implementation class MondatService
 */
@Stateless
@LocalBean
public class MondatService implements MondatServiceRemote, MondatServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager entityManager;
	
    public MondatService() {
   
    }
 
    @Override
   public List<Resource> findResourceByCompetenceAndAvailability(Competence competence, 	EnumSet<Availability> availables){
	  List<Resource> list= entityManager.createQuery("select r from Resource r inner join r.levels l where l.competences =:compt "
	  		+ "and r.availability in :available ")
			  .setParameter("available", availables)
			  .setParameter("compt", competence)
			 .getResultList();
			  

	return list;
	   
   }

	@Override
	public void addTerm(Term term) {
		
		
		
		
		
		
		
		
	}

	@Override
	public Date calculateEndDateTerm(Term term) {

		
		Calendar cal = Calendar.getInstance();
		cal.setTime(term.getDateStart());
		cal.add(Calendar.DAY_OF_MONTH, term.getNumberofDaysTerm());
		Date dateFin= cal.getTime();
		int days = getWorkingDaysBetweenTwoDates(term.getDateStart(),dateFin);
			cal.add(Calendar.DAY_OF_MONTH, days);
		days= getWorkingDaysBetweenTwoDates(dateFin, cal.getTime());
		
		
		int rest =0;
		
		do{
			
		}while(rest ==0);
		
		
		return null;
	}
    
	
	
	public  int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return workDays;
	}
 

}
