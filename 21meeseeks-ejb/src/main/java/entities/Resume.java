package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Resume implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idResume;
	@OneToOne(mappedBy = "resume")
	private Resource resource;
	private String description;
	@ManyToMany
	private List<Certificate> certificates;
	@OneToMany(mappedBy = "resume")
	private List<Degree> listDegree;
	@OneToMany(mappedBy = "resume")
	private List<JobDate> listJob;

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	public List<Degree> getListDegree() {
		return listDegree;
	}

	public void setListDegree(List<Degree> listDegree) {
		this.listDegree = listDegree;
	}

	public List<JobDate> getListJob() {
		return listJob;
	}

	public void setListJob(List<JobDate> listJob) {
		this.listJob = listJob;
	}

}
