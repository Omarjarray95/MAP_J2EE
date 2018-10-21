package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Term implements Serializable{
	
	@EmbeddedId
	private PkTerm pkTerm;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnd;
	private float dailyFee;
	private String description;
	

	@ManyToOne
	@JoinColumn(name="idResource",referencedColumnName="idUser",insertable=false,updatable=false)
	private Resource resources;
	@ManyToOne
	@JoinColumn(name="idProject",referencedColumnName="idProject",insertable=false,updatable=false)
	private Project projects;
	
	@OneToOne
	private Note note;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public PkTerm getPkTerm() {
		return pkTerm;
	}
	public void setPkTerm(PkTerm pkTerm) {
		this.pkTerm = pkTerm;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public float getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(float dailyFee) {
		this.dailyFee = dailyFee;
	}
	public Resource getResources() {
		return resources;
	}
	public void setResources(Resource resources) {
		this.resources = resources;
	}
	public Project getProjects() {
		return projects;
	}
	public void setProjects(Project projects) {
		this.projects = projects;
	}
	
}
