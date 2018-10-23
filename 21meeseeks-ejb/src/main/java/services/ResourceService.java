package services;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.ResourceServiceLocal;
import interfaces.ResourceServiceRemote;
import entities.Resource;

/**
 * Session Bean implementation class ResourceService
 */
@Stateless
@LocalBean
public class ResourceService implements ResourceServiceRemote, ResourceServiceLocal {

	@PersistenceContext(unitName="21meeseeks-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ResourceService() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public int ajoutRessource(Resource r) {
		em.persist(r);
		return r.getIdUser();
	}

}
