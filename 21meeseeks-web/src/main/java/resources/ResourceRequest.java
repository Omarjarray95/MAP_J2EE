package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import Interface.ResourceServiceLocal;

@Path("resource")
@RequestScoped
public class ResourceRequest {
	@EJB(beanName="ResourceServiceLocal")
	ResourceServiceLocal resourceRequest;
	

}
