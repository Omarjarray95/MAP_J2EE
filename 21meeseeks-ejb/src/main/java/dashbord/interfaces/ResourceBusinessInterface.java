package dashbord.interfaces;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import entities.Resource;

@Remote
public interface ResourceBusinessInterface {

	public int countResource();
	public Resource getHigherSalary();
	
}
