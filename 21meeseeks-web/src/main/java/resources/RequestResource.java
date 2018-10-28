package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.LeaveRequest;
import entities.ProjectRequest;
import interfaces.RequestServiceLocal;

@Path("Request")
@RequestScoped
public class RequestResource 
{
	@EJB(beanName = "RequestService")
	RequestServiceLocal RSL;
	
	@GET
	@Path("Say")
	public String SayHello()
	{
		String S = "Hello";
		return S;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddProjectRequest(ProjectRequest PR) 
	{
		int PRID = RSL.AddProjectRequest(PR);
		return Response.status(Status.CREATED).entity(PR).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ShowAllProjectRequests() 
	{
		if (!RSL.ShowAllProjectRequests().isEmpty())
		{
			return Response.ok(RSL.ShowAllProjectRequests()).build();
		}
		else
		{
			return Response.status(Status.NO_CONTENT).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateProjectRequest(ProjectRequest PR) 
	{
		if (RSL.UpdateProjectRequest(PR))
		{
			return Response.status(Status.OK).entity(PR).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).entity(PR).build();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteProjectRequest(ProjectRequest PR) 
	{
		if (RSL.DeleteProjectRequest(PR.getIdRequest()))
		{
			return Response.status(Status.OK).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Leave")
	public Response AddLeaveRequest(LeaveRequest LR) 
	{
		int LRID = RSL.SendLeaveRequest(LR);
		return Response.status(Status.CREATED).entity(LR).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Leave")
	public Response ShowAllLeaveRequests() 
	{
		if (!RSL.ShowAllLeaveRequests().isEmpty())
		{
			return Response.ok(RSL.ShowAllLeaveRequests()).build();
		}
		else
		{
			return Response.status(Status.NO_CONTENT).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Leave")
	public Response UpdateLeaveRequest(LeaveRequest LR) 
	{
		if (RSL.UpdateLeaveRequest(LR))
		{
			return Response.status(Status.OK).entity(LR).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).entity(LR).build();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Leave")
	public Response DeleteLeaveRequest(LeaveRequest LR) 
	{
		if (RSL.DeleteLeaveRequest(LR.getIdLeaveRequest()))
		{
			return Response.status(Status.OK).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
