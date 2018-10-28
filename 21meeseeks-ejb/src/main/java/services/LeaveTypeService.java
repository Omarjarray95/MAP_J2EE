package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.LeaveType;
import interfaces.LeaveTypeServiceLocal;
import interfaces.LeaveTypeServiceRemote;

@Stateless
@LocalBean
public class LeaveTypeService implements LeaveTypeServiceLocal, LeaveTypeServiceRemote
{
	@PersistenceContext(unitName="21meeseeks-ejb")
	EntityManager em;

	@Override
	public int AddLeaveType(LeaveType LT) 
	{
		em.persist(LT);
		return LT.getIdLeaveType();
	}

	@Override
	public List<LeaveType> ShowAllLeaveTypes() 
	{
		return em.createQuery("SELECT t FROM LeaveType t ORDER BY t.name", LeaveType.class).getResultList();
	}

	@Override
	public boolean UpdateLeaveType(LeaveType LT) 
	{
		try 
		{
			LeaveType LTTU = em.find(LeaveType.class, LT.getIdLeaveType());

			LTTU.setName(LT.getName());
			LTTU.setDescription(LT.getDescription());
			
			em.merge(LTTU);
			
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@Override
	public boolean DeleteLeaveType(int LTID) 
	{
		LeaveType LT = em.find(LeaveType.class, LTID);
		try 
		{
			em.remove(LT);
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
}
