package resources;

import interfaces.ClientServiceLocal;
import interfaces.ProjectServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Project;
import entities.ProjectRequest;

@Path("project")
@RequestScoped
public class ProjectResource {
	@EJB(beanName="ProjectService")
	ProjectServiceLocal PSL;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProject(Project p)
	{
		Project pr=PSL.addProject(p);
		return Response.status(Status.CREATED).entity(PSL.projectoJson(pr)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response allProjects()
	{
		return Response.status(Status.CREATED).entity(PSL.projectlisttoJson(PSL.getAllProjects())).build();
	
	}
	
	
}
