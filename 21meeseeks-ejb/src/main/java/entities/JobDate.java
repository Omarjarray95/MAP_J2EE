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
public class JobDate implements Serializable {
	@EmbeddedId
	private JobDatePK jobDatePK;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@ManyToOne
	@JoinColumn(name = "idResume", referencedColumnName = "idResume", insertable = false, updatable = false)
	private Resume resume;
	@ManyToOne
	@JoinColumn(name = "idSociety", referencedColumnName = "idSociety", insertable = false, updatable = false)
	private Society society;

	public JobDatePK getJobDatePK() {
		return jobDatePK;
	}

	public void setJobDatePK(JobDatePK jobDatePK) {
		this.jobDatePK = jobDatePK;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

}
