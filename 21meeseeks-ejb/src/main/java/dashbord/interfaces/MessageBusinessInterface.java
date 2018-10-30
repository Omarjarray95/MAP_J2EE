package dashbord.interfaces;

import java.util.List;

import entities.Resource;

public interface MessageBusinessInterface {

	public List<Resource> getMostCommunicated();
	public Resource getMostHelpful();
	
}
