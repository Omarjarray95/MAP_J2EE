package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Organigram implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrganigram;
	@Column(nullable=true)
	private String programName;
	private String projectManagerName;
	private String projectName;
	@ManyToOne
	private Client client;
	private String financialManager;
	private String assignmentManager;
	@Temporal(TemporalType.TIMESTAMP)
	private Date organigramDate;
	
	
	
	
	
	public int getIdOrganigram() {
		return idOrganigram;
	}
	public void setIdOrganigram(int idOrganigram) {
		this.idOrganigram = idOrganigram;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProjectManagerName() {
		return projectManagerName;
	}
	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getFinancialManager() {
		return financialManager;
	}
	public void setFinancialManager(String financialManager) {
		this.financialManager = financialManager;
	}
	public String getAssignmentManager() {
		return assignmentManager;
	}
	public void setAssignmentManager(String assignmentManager) {
		this.assignmentManager = assignmentManager;
	}
	public Date getOrganigramDate() {
		return organigramDate;
	}
	public void setOrganigramDate(Date organigramDate) {
		this.organigramDate = organigramDate;
	}
	
	
	
	
	
	
	
	

}
