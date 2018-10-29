package dashbord.interfaces;

import java.time.LocalDate;
import java.util.Date;

public interface TermsBusinessInterface {

	public Integer countTerms();
	public Integer countTermsInPeriod(Date dateFrom , Date dateTo);
	public Integer countTermsByCompetence(Integer idCompetence);
	public Integer countTermsByProject(Integer project);
	public float countTermsFreelanceVSEmployee();
	public float totalIncome();
	public float monthlyIncomeBy(LocalDate year);
	public float incomeInPeriod(Date dateFrom , Date dateTo);
	
	
}
