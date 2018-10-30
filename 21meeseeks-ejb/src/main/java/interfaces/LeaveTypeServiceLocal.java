package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.LeaveType;
import entities.ProjectRequest;

@Local
public interface LeaveTypeServiceLocal 
{
	public int AddLeaveType(LeaveType LT);
	
	public List<LeaveType> ShowAllLeaveTypes();
	
	public boolean UpdateLeaveType(LeaveType LT);
	
	public boolean DeleteLeaveType(int LTID);
}
