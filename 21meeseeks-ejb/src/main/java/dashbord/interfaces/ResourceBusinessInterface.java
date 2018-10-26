package dashbord.interfaces;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import entities.Resource;

@Remote
public interface ResourceBusinessInterface {

	public Integer countResource();
	public Resource getHigherSalary();
	public Integer countResourceBySeniority(Integer idSeniority);
	public Integer countResourceFreelancer();
	public Integer countResourceLevio();
	public Integer countResourceAvailable();
	public Integer countResourceInDayOff();
	public Integer countResourceByField(Integer idField);
	public Integer countResourceByCompetence(Integer idCompetence);
	public Integer countResourceDynamic(Integer idSeniority , Integer idCompetence , Integer idField , boolean available , boolean dayOff , boolean type);
	
}
