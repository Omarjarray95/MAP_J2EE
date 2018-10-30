package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Client;
import entities.Competence;
import entities.LeaveRequest;
import entities.LeaveType;
import entities.ProjectRequest;
import entities.Resource;
import enums.RequestStatus;
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
		PR.setDepositDate(new Date());
		PR.setRequestStatus(RequestStatus.SENT);
		PR.setArchived(false);
		List<Competence> LC0 = new ArrayList<>();
		for (Competence C : PR.getCompetences())
		{
			try
			{
				Competence C0 = em.find(Competence.class, C.getIdCompetence());
				LC0.add(C0);
			}
			catch (Exception E)
			{
				System.out.println(E);
			}
		}
		PR.setCompetences(LC0);
		try
		{
			Client Cl = em.find(Client.class, PR.getClient().getIdUser());
			PR.setClient(Cl);
		}
		catch(Exception E)
		{
			PR.setClient(null);
		}
		em.persist(PR);
		System.out.println("Your Project Request Has Been Sent To The Administrator");
		return PR.getIdRequest();
	}

	@Override
	public List<ProjectRequest> ShowPendingProjectRequests() 
	{
		return em.createQuery("SELECT r FROM ProjectRequest r Where r.archived = FALSE ORDER BY r.depositDate DESC", ProjectRequest.class).getResultList();
	}
	
	@Override
	public List<ProjectRequest> ShowArchivedProjectRequests() 
	{
		return em.createQuery("SELECT r FROM ProjectRequest r Where r.archived = TRUE ORDER BY r.depositDate DESC", ProjectRequest.class).getResultList();
	}

	@Override
	public boolean UpdateProjectRequest(ProjectRequest PR) 
	{
		try 
		{
			ProjectRequest PRTU = em.find(ProjectRequest.class, PR.getIdRequest());

			PRTU.setDepositDate(new Date());
			if (PR.getNameProject() != null)
			{
				PRTU.setNameProject(PR.getNameProject());
			}
			if (PR.getDateStartProject() != null)
			{
				PRTU.setDateStartProject(PR.getDateStartProject());
			}
			if (PR.getDateEndProject() != null)
			{
				PRTU.setDateEndProject(PR.getDateEndProject());
			}
			if (PR.getDescriptionProject() != null)
			{
				PRTU.setDescriptionProject(PR.getDescriptionProject());
			}
			if (PR.getCompetences() != null)
			{
				PRTU.setCompetences(PR.getCompetences());
			}
			if (PR.getResourcesNumber() != 0)
			{
				PRTU.setResourcesNumber(PR.getResourcesNumber());
			}
			if (PR.getClient() != null)
			{
				PRTU.setClient(PR.getClient());
			}
			if (PR.getPresentedBy() != null)
			{
				PRTU.setPresentedBy(PR.getPresentedBy());
			}
			if (PR.getComments() != null)
			{
				PRTU.setComments(PR.getComments());
			}
			
			em.merge(PRTU);
			
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@Override
	public boolean ArchiveProjectRequest(int PRID)
	{
		ProjectRequest PR = em.find(ProjectRequest.class, PRID);
		try 
		{
			PR.setArchived(true);
			PR.setRequestStatus(RequestStatus.ARCHIVED);
			em.merge(PR);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	@Override
	public boolean DeArchiveProjectRequest(int PRID) 
	{
		ProjectRequest PR = em.find(ProjectRequest.class, PRID);
		try 
		{
			PR.setArchived(false);
			PR.setRequestStatus(RequestStatus.NOT_ARCHIVED);
			em.merge(PR);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	@Override
	public boolean UpdateProjectRequestStatus(ProjectRequest PR) 
	{
		try 
		{
			ProjectRequest PRTU = em.find(ProjectRequest.class, PR.getIdRequest());
			
			PRTU.setRequestStatus(PR.getRequestStatus());
			PRTU.setReviewDate(new Date());
			
			em.merge(PRTU);
			
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	@Override
	public List<ProjectRequest> SearchThroughProjectRequests(boolean archived, Map<String, Date> DateMap, List<String> StatusList, List<String> Strings) 
	{
		List<ProjectRequest> LPR = new ArrayList<>();
		if (!archived)
		{
			LPR = ShowPendingProjectRequests();
		}
		else
		{
			LPR = ShowArchivedProjectRequests();
		}
		if (DateMap.get("DepositDateMax") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("DepositDateMax").compareTo(LPR.get(i).getDepositDate()) < 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("DepositDateMin") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("DepositDateMin").compareTo(LPR.get(i).getDepositDate()) > 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("StartDateMax") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("StartDateMax").compareTo(LPR.get(i).getDateStartProject()) < 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("StartDateMin") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("StartDateMin").compareTo(LPR.get(i).getDateStartProject()) > 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("EndDateMax") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("EndDateMax").compareTo(LPR.get(i).getDateEndProject()) < 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("EndDateMin") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("EndDateMin").compareTo(LPR.get(i).getDateEndProject()) > 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("ReviewDateMax") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("ReviewDateMax").compareTo(LPR.get(i).getReviewDate()) < 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (DateMap.get("ReviewDateMin") != null && !LPR.isEmpty())
		{
			for (int i = LPR.size()-1; i > -1; i--)
			{
				if (DateMap.get("ReviewDateMin").compareTo(LPR.get(i).getReviewDate()) > 0)
				{
					LPR.remove(LPR.get(i));
				}
			}
		}
		if (!StatusList.isEmpty() && !LPR.isEmpty())
		{
			for (String S : StatusList)
			{
				for (int i = LPR.size()-1; i > -1; i--)
				{
					if (!LPR.get(i).getRequestStatus().toString().equals(S))
					{
						LPR.remove(LPR.get(i));
					}
				}
			}
		}
		if (!Strings.isEmpty())
		{
			for(int i = LPR.size()-1; i > -1; i--)
			{
				for (String S : Strings)
				{
					System.out.println(S);
					if (!LPR.isEmpty())
					{
						if (!VerifyContent(LPR.get(i), S))
						{
							LPR.remove(LPR.get(i));
						}
					}
				}
			}
		}
		return LPR;
	}
	
	public boolean VerifyContent(ProjectRequest PR, String S)
	{
		if (PR.getNameProject().contains(S))
		{
			return true;
		}
		else if (PR.getDescriptionProject().contains(S))
		{
			return true;
		}
		else if (PR.getPresentedBy().contains(S))
		{
			return true;
		}
		else if (PR.getComments().contains(S))
		{
			return true;
		}
		/*else if (PR.getClient().getClientName().contains(S) || PR.getClient().getEmail().contains(S))
		{
			return true;
		}*/
		else
		{
			return false;
		}
	}
	
	@Override
	public List<ProjectRequest> SortProjectRequests(boolean archived, String Criteria, String Direction) 
	{
		return em.createQuery("SELECT r FROM ProjectRequest r Where r.archived = " + archived +" ORDER BY r." + Criteria + " " + Direction, ProjectRequest.class).getResultList();
	}
	
	@Override
	public List<ProjectRequest> ShowClientProjectRequests(Client C) 
	{
		Client C0 = em.find(Client.class, C.getIdUser());
		return em.createQuery("SELECT r FROM ProjectRequest r WHERE r.client=:c", ProjectRequest.class).setParameter("c", C0).getResultList();
	}
	
	@Override
	public List<ProjectRequest> ShowProjectRequestsByCompetencesNeeded(List<Competence> LC) 
	{
		List<Competence> LC0 = new ArrayList<>();
		List<ProjectRequest> LPR = new ArrayList<>();
		for (Competence C : LC)
		{
			Competence C0 = em.find(Competence.class, C.getIdCompetence());
			LC0.add(C0);
		}
		for (Competence C : LC0)
		{
			for (ProjectRequest PR : ShowPendingProjectRequests())
			{
				if (PR.getCompetences().contains(C) && !LPR.contains(PR))
				{
					LPR.add(PR);
				}
			}
		}
		return LPR;
	}

	@Override
	public int SendLeaveRequest(LeaveRequest LR) 
	{
		LeaveType LT = em.find(LeaveType.class, LR.getLeaveType().getIdLeaveType());
		LR.setLeaveType(LT);
		LR.setDepositDate(new Date());
		LR.setArchived(false);
		em.persist(LR);
		System.out.println("Your Leave Request Has Been Sent To The Administrator");
		return LR.getIdLeaveRequest();
	}

	@Override
	public List<LeaveRequest> ShowArchivedLeaveRequests() 
	{
		return em.createQuery("SELECT r FROM LeaveRequest r Where r.archived = true ORDER BY r.depositDate DESC", LeaveRequest.class).getResultList();
	}
	
	@Override
	public List<LeaveRequest> ShowPendingLeaveRequests() 
	{
		return em.createQuery("SELECT r FROM LeaveRequest r Where r.archived = false ORDER BY r.depositDate DESC", LeaveRequest.class).getResultList();
	}

	@Override
	public boolean UpdateLeaveRequest(LeaveRequest LR) 
	{
		try 
		{
			LeaveRequest LRTU = em.find(LeaveRequest.class, LR.getIdLeaveRequest());

			if (LR.getFromDate() != null)
			{
				LRTU.setFromDate(LR.getFromDate());
			}
			if (LR.getToDate() != null)
			{
				LRTU.setToDate(LR.getToDate());
			}
			if (LR.getDescription() != null)
			{
				LRTU.setDescription(LR.getDescription());
			}
			if (LR.getLeaveType() != null)
			{
				LeaveType LT0 = em.find(LeaveType.class, LR.getLeaveType().getIdLeaveType());
				LRTU.setLeaveType(LT0);
			}
			LRTU.setDepositDate(new Date());
			
			em.merge(LRTU);
			
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@Override
	public boolean ArchiveLeaveRequest(int LRID) 
	{
		LeaveRequest LR = em.find(LeaveRequest.class, LRID);
		try 
		{
			LR.setArchived(true);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	@Override
	public boolean DeArchiveLeaveRequest(int LRID) 
	{
		LeaveRequest LR = em.find(LeaveRequest.class, LRID);
		try 
		{
			LR.setArchived(false);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	@Override
	public List<LeaveRequest> SortLeaveRequests(String Criteria, String Direction) 
	{
		return em.createQuery("SELECT r FROM LeaveRequest r" + " ORDER BY r." + Criteria + " " + Direction, LeaveRequest.class).getResultList();
	}
	
	@Override
	public List<LeaveRequest> ShowResourceLeaveRequests(Resource R) 
	{
		Resource R0 = em.find(Resource.class, R.getIdUser());
		return em.createQuery("SELECT r FROM LeaveRequest r WHERE r.resource=:rsc", LeaveRequest.class).setParameter("rsc", R0).getResultList();
	}
	
	@Override
	public List<LeaveRequest> ShowLeaveRequestsByType(LeaveType LT) 
	{
		LeaveType LT0 = em.find(LeaveType.class, LT.getIdLeaveType());
		return em.createQuery("SELECT r FROM LeaveRequest r WHERE r.leaveType=:lt", LeaveRequest.class).setParameter("lt", LT0).getResultList();
	}
	
}
