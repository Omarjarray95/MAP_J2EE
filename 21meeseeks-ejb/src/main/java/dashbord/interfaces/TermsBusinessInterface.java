package dashbord.interfaces;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import entities.Term;

public interface TermsBusinessInterface {

	public Integer countTerms();
	public Integer countTermsInPeriod(Date dateFrom , Date dateTo);
	public Integer countTermsByCompetence(Integer idCompetence);
	public Integer countTermsByProject(Integer project);
	public float countTermsFreelanceVSEmployee();
	public float totalIncome();
	public float monthlyIncomeBy(LocalDate year);
	public Double incomeInPeriod(Date dateFrom , Date dateTo);
	public List<Term> getTermsByResource(Integer id,Date dateFrom , Date dateTo);
	public Double AVGTermDays(Date dateFrom , Date dateTo);
	
	
}
