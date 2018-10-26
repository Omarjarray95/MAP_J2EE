package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.RequestServiceLocal;
import interfaces.RequestServiceRemote;

@Stateless
@LocalBean
public class RequestService implements RequestServiceLocal, RequestServiceRemote
{
	@PersistenceContext(unitName="21meeseeks-ejb")
	EntityManager em;

	public RequestService() 
	{
		
	}
	public String seyhello()
	{
		return "hello";
	}
	
}
