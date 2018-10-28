package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.LeaveRequest;
import entities.LeaveType;
import entities.ProjectRequest;
import interfaces.RequestServiceLocal;
import interfaces.RequestServiceRemote;

@Stateless
@LocalBean
public class RequestService implements RequestServiceLocal, RequestServiceRemote
{
	@PersistenceContext(unitName="21meeseeks-ejb")
	EntityManager em;

	@Override
	public int AddProjectRequest(ProjectRequest PR) 
	{
		em.persist(PR);
		System.out.println("Your Project Request Has Been Sent To The Administrator");
		return PR.getIdRequest();
	}

	@Override
	public List<ProjectRequest> ShowAllProjectRequests() 
	{
		return em.createQuery("SELECT r FROM ProjectRequest r ORDER BY r.depositDate DESC", ProjectRequest.class).getResultList();
	}

	@Override
	public boolean UpdateProjectRequest(ProjectRequest PR) 
	{
		try 
		{
			ProjectRequest PRTU = em.find(ProjectRequest.class, PR.getIdRequest());

			PRTU.setDepositDate(PR.getDepositDate());
			PRTU.setNameProject(PR.getNameProject());
			PRTU.setDateStartProject(PR.getDateStartProject());
			PRTU.setDateEndProject(PR.getDateEndProject());
			PRTU.setDescriptionProject(PR.getDescriptionProject());
			PRTU.setCompetences(PR.getCompetences());
			PRTU.setResourcesNumber(PR.getResourcesNumber());
			PRTU.setClient(PR.getClient());
			PRTU.setPresentedBy(PR.getPresentedBy());
			PRTU.setComments(PR.getComments());
			
			em.merge(PRTU);
			
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@Override
	public boolean DeleteProjectRequest(int PRID)
	{
		ProjectRequest PR = em.find(ProjectRequest.class, PRID);
		try 
		{
			em.remove(PR);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@Override
	public int SendLeaveRequest(LeaveRequest LR) 
	{
		LeaveType LT = em.find(LeaveType.class, LR.getLeaveType().getIdLeaveType());
		LR.setLeaveType(LT);
		em.persist(LR);
		System.out.println("Your Leave Request Has Been Sent To The Administrator");
		return LR.getIdLeaveRequest();
	}

	@Override
	public List<LeaveRequest> ShowAllLeaveRequests() 
	{
		return em.createQuery("SELECT r FROM LeaveRequest r ORDER BY r.depositDate DESC", LeaveRequest.class).getResultList();
	}

	@Override
	public boolean UpdateLeaveRequest(LeaveRequest LR) 
	{
		try 
		{
			LeaveRequest LRTU = em.find(LeaveRequest.class, LR.getIdLeaveRequest());

			LRTU.setFromDate(LR.getFromDate());
			LRTU.setToDate(LR.getToDate());
			LRTU.setDepositDate(LR.getDepositDate());
			LRTU.setResource(LR.getResource());
			LRTU.setDescription(LR.getDescription());
			LRTU.setLeaveType(LR.getLeaveType());
			
			em.merge(LRTU);
			
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@Override
	public boolean DeleteLeaveRequest(int LRID) 
	{
		LeaveRequest LR = em.find(LeaveRequest.class, LRID);
		try 
		{
			em.remove(LR);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
}
