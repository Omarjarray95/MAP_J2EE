package services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import interfaces.ProjectServiceLocal;
import interfaces.ProjectServiceRemote;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import entities.Client;
import entities.ClientCategory;
import entities.Competence;
import entities.Level;
import entities.Project;
import entities.ProjectRequest;
import entities.Resource;
import enums.ProjectType;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
@LocalBean
public class ProjectService implements ProjectServiceRemote, ProjectServiceLocal {
	@PersistenceContext(unitName="21meeseeks-ejb")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ProjectService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Project addProject(Project p) {
		p.setClient(em.find(Client.class, p.getClient().getIdUser()));
		em.persist(p);
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	public Project addProjectfromRequest(ProjectRequest prequest) {
		
			Project p=new Project();
			p.setClient(prequest.getClient());
			p.setCompetences(prequest.getCompetences());
			p.setDateStart(prequest.getDateStartProject());
			p.setDateEnd(prequest.getDateEndProject());
			p.setName(prequest.getNameProject());
			Date now=new Date();
			/*if(now.before(prequest.getDateStartProject()))
			{
				p.setProjectType(ProjectType.valueOf("NEW"));
			}
			else if(now.after(prequest.getDateEndProject()))
			{
				p.setProjectType(ProjectType.valueOf("DONE"));
			}
			else
			{
				p.setProjectType(ProjectType.valueOf("DONE"));
			}
			*/
			em.persist(p);
		return p;
	}

	@Override
	public List<Project> getAllProjects() {
		Date d=new Date();
		Project p=new Project();
		Client c=new Client();
		em.persist(c);
		p.setClient(c);
		p.setName("theproject");
		p.setDateStart(d);
		Competence c1=new Competence();
		c1.setLabel("jee");
		em.persist(c1);
		Resource r1=new Resource();
		Resource r2=new Resource();
		Level l1=new Level();
		l1.setCompetences(c1);
		l1.setResources(r1);
		l1.setLevel(50);
		Level l2=new Level();
		l2.setCompetences(c1);
		l2.setLevel(2);
		em.persist(r1);
		em.persist(r2);
		em.persist(l1);
		em.persist(l2);
		em.persist(p);
		TypedQuery<Project> query=em.createQuery("SELECT e from Project e",Project.class);
		List<Project> cf=query.getResultList();
		return cf;

	}
	@Override
	public String projectlisttoJson(List<Project> c) {
		 String test="";
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode main = mapper.createObjectNode();
			try {
				 test=mapper.writeValueAsString(c);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return test;
	}
	@Override
	public String projectoJson(Project c) {
		// TODO Auto-generated method stub
	 if(c.getDateStart().after(new Date()))
	 {
		 c.setProjectType(ProjectType.valueOf("NEW"));
	 }
	 else if(c.getDateEnd().before(new Date()))
	 {
		 c.setProjectType(ProjectType.valueOf("DONE"));

	 }
	 else
	 {
		 c.setProjectType(ProjectType.valueOf("ONGOING"));

	 }
	 em.merge(c);
		 String test="";
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode main = mapper.createObjectNode();
		try {
			 test=mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return test;
		}

}
