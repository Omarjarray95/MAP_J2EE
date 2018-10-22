package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Society implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSociety;
	private String name;
	private String description;
	@OneToMany(mappedBy = "society")
	private List<JobDate> listJob;

	public int getIdSociety() {
		return idSociety;
	}

	public void setIdSociety(int idSociety) {
		this.idSociety = idSociety;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<JobDate> getListJob() {
		return listJob;
	}

	public void setListJob(List<JobDate> listJob) {
		this.listJob = listJob;
	}
	

}
