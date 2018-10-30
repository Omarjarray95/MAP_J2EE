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

import entities.LeaveType;
import entities.ProjectRequest;
import interfaces.LeaveTypeServiceLocal;
import interfaces.RequestServiceLocal;

@Path("Leavetype")
@RequestScoped
public class LeaveTypeResource 
{
	@EJB(beanName = "LeaveTypeService")
	LeaveTypeServiceLocal LTSL;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddLeaveType(LeaveType LT) 
	{
		int LTID = LTSL.AddLeaveType(LT);
		return Response.status(Status.CREATED).entity(LT).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ShowAllLeaveTypes() 
	{
		if (!LTSL.ShowAllLeaveTypes().isEmpty())
		{
			return Response.ok(LTSL.ShowAllLeaveTypes()).build();
		}
		else
		{
			return Response.status(Status.NO_CONTENT).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateLeaveType(LeaveType LT) 
	{
		if (LTSL.UpdateLeaveType(LT))
		{
			return Response.status(Status.OK).entity(LT).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).entity(LT).build();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteLeaveType(LeaveType LT) 
	{
		if (LTSL.DeleteLeaveType(LT.getIdLeaveType()))
		{
			return Response.status(Status.OK).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
