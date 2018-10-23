package dashboard.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dashbord.interfaces.ResourceBusinessInterface;
import entities.Resource;

@Stateless
public class ResourceBusiness implements ResourceBusinessInterface{

	@PersistenceContext(name="21meeseeks-ejb")
	private EntityManager em;
	
	@Override
	public int countResource() {
		// TODO Auto-generated method stub
		int count = (Integer)em.createQuery("SELECT count(r) FROM Resource r").getSingleResult();
		return count;
	}

	@Override
	public Resource getHigherSalary() {
		// TODO Auto-generated method stub
		return null;
	}

}
