package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class JobDatePK implements Serializable {

	private int idResume;
	private int idSociety;
	private int idJobDate;

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	public int getIdSociety() {
		return idSociety;
	}

	public void setIdSociety(int idSociety) {
		this.idSociety = idSociety;
	}

	public int getIdJobDate() {
		return idJobDate;
	}

	public void setIdJobDate(int idJobDate) {
		this.idJobDate = idJobDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idJobDate;
		result = prime * result + idResume;
		result = prime * result + idSociety;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobDatePK other = (JobDatePK) obj;
		if (idJobDate != other.idJobDate)
			return false;
		if (idResume != other.idResume)
			return false;
		if (idSociety != other.idSociety)
			return false;
		return true;
	}

}
