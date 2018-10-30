package interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.Client;
import entities.Competence;
import entities.LeaveRequest;
import entities.LeaveType;
import entities.ProjectRequest;
import entities.Resource;

@Local
public interface RequestServiceLocal 
{
	public int AddProjectRequest(ProjectRequest PR);
	
	public List<ProjectRequest> ShowPendingProjectRequests();
	
	public List<ProjectRequest> ShowArchivedProjectRequests();
	
	public boolean UpdateProjectRequest(ProjectRequest PR);
	
	public boolean ArchiveProjectRequest(int PRID);
	
	public boolean DeArchiveProjectRequest(int PRID);
	
	public boolean UpdateProjectRequestStatus(ProjectRequest PR);
	
	public List<ProjectRequest> SearchThroughProjectRequests(boolean archived, Map<String, Date> DateMap, List<String> StatusList, List<String> Strings);
	
	public List<ProjectRequest> SortProjectRequests(boolean archived, String Criteria, String Direction);
	
	public List<ProjectRequest> ShowClientProjectRequests(Client C);
	
	public List<ProjectRequest> ShowProjectRequestsByCompetencesNeeded(List<Competence> LC);
	
	public int SendLeaveRequest(LeaveRequest LR);
	
	public List<LeaveRequest> ShowArchivedLeaveRequests();
	
	public List<LeaveRequest> ShowPendingLeaveRequests();
	
	public boolean UpdateLeaveRequest(LeaveRequest LR);
	
	public boolean ArchiveLeaveRequest(int LRID);
	
	public boolean DeArchiveLeaveRequest(int LRID);
	
	public List<LeaveRequest> SortLeaveRequests(String Criteria, String Direction);
	
	public List<LeaveRequest> ShowResourceLeaveRequests(Resource R);
	
	public List<LeaveRequest> ShowLeaveRequestsByType(LeaveType LT);
}
