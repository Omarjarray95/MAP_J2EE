package dashbord.interfaces;

import java.util.Date;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import entities.Resource;

@Remote
public interface ResourceBusinessInterface {

	public Integer countResource();
	public Map<Resource,Long> getMostTerm(Integer limit,Date dateFrom , Date dateTo);
	public Integer countResourceBySeniority(Integer idSeniority);
	public Integer countResourceFreelancer();
	public Integer countResourceLevio();
	public Integer countResourceAvailable();
	public Integer countResourceInDayOff();
	public Integer countResourceByField(Integer idField);
	public Integer countResourceByCompetence(Integer idCompetence);
	public Integer countResourceDynamic(Integer idSeniority , Integer idCompetence , Integer idField , String available , boolean dayOff , boolean type);
	
}
