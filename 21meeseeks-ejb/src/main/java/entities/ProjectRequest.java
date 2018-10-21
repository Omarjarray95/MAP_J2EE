package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class ProjectRequest implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRequest;
	private Date depositDate;
	private String nameProject;
	@Temporal(TemporalType.DATE)
	private Date dateStartProject;
	@Temporal(TemporalType.DATE)
	private Date dateEndProject;
	private String descriptionProject;
	@ManyToMany
	private List<Competence> competences; 
	private int resourcesNumber;
	@ManyToOne
	private Client client;
	
	public int getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
	public Date getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}
	public String getNameProject() {
		return nameProject;
	}
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	public Date getDateStartProject() {
		return dateStartProject;
	}
	public void setDateStartProject(Date dateStartProject) {
		this.dateStartProject = dateStartProject;
	}
	public Date getDateEndProject() {
		return dateEndProject;
	}
	public void setDateEndProject(Date dateEndProject) {
		this.dateEndProject = dateEndProject;
	}
	public String getDescriptionProject() {
		return descriptionProject;
	}
	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}
	public List<Competence> getCompetences() {
		return competences;
	}
	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}
	public int getResourcesNumber() {
		return resourcesNumber;
	}
	public void setResourcesNumber(int resourcesNumber) {
		this.resourcesNumber = resourcesNumber;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
}
