package interfaces;

import javax.ejb.Local;

import entities.Resource;

@Local
public interface ResourceServiceLocal {

	public int ajoutRessource(Resource r);
	
}
