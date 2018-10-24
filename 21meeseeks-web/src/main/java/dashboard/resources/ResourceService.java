package dashboard.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import dashbord.interfaces.ResourceBusinessInterface;

@Stateless
@Path("test")
public class ResourceService {

	@EJB
	ResourceBusinessInterface rbi;
	
	@GET
	public int countResource(){
		return rbi.countResource();
	}
	
}
