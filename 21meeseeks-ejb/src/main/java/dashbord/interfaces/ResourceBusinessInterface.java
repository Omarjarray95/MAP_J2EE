package dashbord.interfaces;

import javax.ejb.LocalBean;

import entities.Resource;

@LocalBean
public interface ResourceBusinessInterface {

	public int countResource();
	public Resource getHigherSalary();
	
}
