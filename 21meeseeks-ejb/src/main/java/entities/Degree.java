package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Degree implements Serializable {
	@EmbeddedId
	private DegreePK degreePK;
	@Temporal(TemporalType.DATE)
	private Date gradYear;
	private String name;
	@ManyToOne
	@JoinColumn(name = "idResume", referencedColumnName = "idResume", insertable = false, updatable = false)
	private Resume resume;
	@ManyToOne
	@JoinColumn(name = "idEstablishment", referencedColumnName = "idEstablishment", insertable = false, updatable = false)
	private Establishment establishment;

	public DegreePK getDegreePK() {
		return degreePK;
	}

	public void setDegreePK(DegreePK degreePK) {
		this.degreePK = degreePK;
	}

	public Date getGradYear() {
		return gradYear;
	}

	public void setGradYear(Date gradYear) {
		this.gradYear = gradYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}
	

}
