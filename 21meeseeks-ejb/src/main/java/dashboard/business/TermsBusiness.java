package dashboard.business;

import java.time.LocalDate;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dashbord.interfaces.TermsBusinessInterface;

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
		return null;
	}

	@Override
	public Integer countTermsByCompetence(Integer idCompetence) {
		// TODO Auto-generated method stub
		return null;
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
	public float incomeInPeriod(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
