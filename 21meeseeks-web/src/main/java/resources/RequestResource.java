package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.json.JsonValue;
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

import entities.Client;
import entities.Competence;
import entities.LeaveRequest;
import entities.LeaveType;
import entities.ProjectRequest;
import entities.Resource;
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
	public Response ShowPendingProjectRequests() 
	{
		if (!RSL.ShowPendingProjectRequests().isEmpty())
		{
			return Response.ok(RSL.ShowPendingProjectRequests()).build();
		}
		else
		{
			return Response.status(Status.NO_CONTENT).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("archive")
	public Response ShowArchivedProjectRequests() 
	{
		if (!RSL.ShowArchivedProjectRequests().isEmpty())
		{
			return Response.ok(RSL.ShowArchivedProjectRequests()).build();
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
		if (RSL.ArchiveProjectRequest(PR.getIdRequest()))
		{
			return Response.status(Status.OK).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response UpdateProjectRequestStatus(ProjectRequest PR) 
	{
		if (RSL.UpdateProjectRequestStatus(PR))
		{
			return Response.status(Status.OK).entity(PR).build();
		}
		else
		{
			return Response.status(Status.BAD_REQUEST).entity(PR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("search")
	public Response SearchThroughProjectRequests(JsonObject JO) 
	{
		boolean archived = JO.getBoolean("archived");
		Map<String, Date> DateMap = new HashMap<String, Date>();
		List<String> StatusList = new ArrayList<>();
		List<String> Strings = new ArrayList<>();
		try 
		{
			if (JO.getOrDefault("DepositDateMax", null) != null)
			{
				String DepositDateMax = JO.getString("DepositDateMax");
				DateMap.put("DepositDateMax", JSONStringToDate(DepositDateMax));
			}
			if (JO.getOrDefault("DepositDateMin", null) != null)
			{
				String DepositDateMin = JO.getString("DepositDateMin");
				DateMap.put("DepositDateMin", JSONStringToDate(DepositDateMin));
			}
			if (JO.getOrDefault("StartDateMax", null) != null)
			{
				String StartDateMax = JO.getString("StartDateMax");
				DateMap.put("StartDateMax", JSONStringToDate(StartDateMax));
			}
			if (JO.getOrDefault("StartDateMin", null) != null)
			{
				String StartDateMin = JO.getString("StartDateMin");
				DateMap.put("StartDateMin", JSONStringToDate(StartDateMin));
			}
			if (JO.getOrDefault("EndDateMax", null) != null)
			{
				String EndDateMax = JO.getString("EndDateMax");
				DateMap.put("EndDateMax", JSONStringToDate(EndDateMax));
			}
			if (JO.getOrDefault("EndDateMin", null) != null)
			{
				String EndDateMin = JO.getString("EndDateMin");
				DateMap.put("EndDateMin", JSONStringToDate(EndDateMin));
			}
			if (JO.getOrDefault("ReviewDateMax", null) != null)
			{
				String ReviewDateMax = JO.getString("ReviewDateMax");
				DateMap.put("ReviewDateMax", JSONStringToDate(ReviewDateMax));
			}
			if (JO.getOrDefault("ReviewDateMin", null) != null)
			{
				String ReviewDateMin = JO.getString("ReviewDateMin");
				DateMap.put("ReviewDateMin", JSONStringToDate(ReviewDateMin));
			}
			if (JO.getOrDefault("StatusList", null) != null)
			{
				List<JsonValue> StatusValues = JO.getJsonArray("StatusList");
				for (JsonValue JV : StatusValues)
				{
					String S0 = JV.toString().substring(1, JV.toString().length() - 1);
					StatusList.add(S0);
				}
			}
			if (JO.getOrDefault("Strings", null) != null)
			{
				String S1 = JO.getString("Strings");
				String[] Parts = S1.split(" ");
				for(int i = 0; i < Parts.length; i++)
				{
					if (Parts[i].length() > 1)
					{
						Strings.add(Parts[i]);
					}
				}
			}
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return Response.ok(RSL.SearchThroughProjectRequests(archived, DateMap, StatusList, Strings)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("sort")
	public Response SortProjectRequests(JsonObject JO) 
	{
		boolean archived = JO.getBoolean("archived");
		String Criteria = JO.getString("Criteria");
		String Direction = JO.getString("Direction");
		return Response.ok(RSL.SortProjectRequests(archived, Criteria, Direction)).build();
	}
	
	public static Date JSONStringToDate(String S) throws java.text.ParseException 
	{
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return SDF.parse(S);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("client")
	public Response ShowClientProjectRequests(Client C) 
	{
		return Response.ok(RSL.ShowClientProjectRequests(C)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("competences")
	public Response ShowProjectRequestsByCompetencesNeeded(List<Competence> LC) 
	{
		return Response.ok(RSL.ShowProjectRequestsByCompetencesNeeded(LC)).build();
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Leave/sort")
	public Response SortLeaveRequests(JsonObject JO) 
	{
		String Criteria = JO.getString("Criteria");
		String Direction = JO.getString("Direction");
		return Response.ok(RSL.SortLeaveRequests(Criteria, Direction)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Leave/resource")
	public Response ShowResourceLeaveRequests(Resource R) 
	{
		return Response.ok(RSL.ShowResourceLeaveRequests(R)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Leave/type")
	public Response ShowLeaveRequestsByType(LeaveType LT) 
	{
		return Response.ok(RSL.ShowLeaveRequestsByType(LT)).build();
	}
	
}
