package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.LeaveRequest;
import entities.ProjectRequest;

@Local
public interface RequestServiceLocal 
{
	public int AddProjectRequest(ProjectRequest PR);
	
	public List<ProjectRequest> ShowAllProjectRequests();
	
	public boolean UpdateProjectRequest(ProjectRequest PR);
	
	public boolean DeleteProjectRequest(int PRID);
	
	public int SendLeaveRequest(LeaveRequest LR);
	
	public List<LeaveRequest> ShowAllLeaveRequests();
	
	public boolean UpdateLeaveRequest(LeaveRequest LR);
	
	public boolean DeleteLeaveRequest(int LRID);
}
