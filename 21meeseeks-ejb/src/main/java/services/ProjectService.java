package services;

import java.util.Arrays;
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
	@Override
	public List<Competence> CompetencesSuggesionByProjectDesc(Project p) {
		List<String> items = Arrays.asList(p.getDescription().split("\\s+"));
	/*	Competence c1=new Competence();
		c1.setDescription("network intrusion security ");
		c1.setLabel("nids");
		Competence c2=new Competence();
		c2.setLabel("peinture");
		c2.setDescription("dessin fazet barsha hajet okhrin");
		em.persist(c1);
		em.persist(c2);*/
		/*CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Competence> c = cb.createQuery(Competence.class);
		Root<Competence> root = c.from(Competence.class);
		c.where(cb.like(root.<String> get("description"),"%all%"));
		 for(String param:items) 

		 {	System.out.println(param);
			 cb.or(cb.like(root.<String> get("description"),"%"+param+"%"));}

		TypedQuery<Competence> tq = em.createQuery(c);*/
		String query="SELECT c FROM Competence c WHERE c.description LIKE  '%everything%' ";
 		TypedQuery<Competence> q = em.createQuery(query,Competence.class);

		 for(String param:items) 

		 {	System.out.println(param);
             query=query+"  OR c.description   LIKE '%" +param + "%' ";
			 }
 		 q = em.createQuery(query,Competence.class);

		List<Competence> results = q.getResultList();
		
		return results;
	}

	@Override
	public Competence testaddCompetence(Competence c) {
		em.persist(c);
		// TODO Auto-generated method stub
		return c;
	}
	
}
