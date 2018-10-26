package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DegreePK implements Serializable {

	private int idResume;
	private int idEstablishment;
	private int idDegree;

	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	public int getIdEstablishment() {
		return idEstablishment;
	}

	public void setIdEstablishment(int idEstablishment) {
		this.idEstablishment = idEstablishment;
	}

	public int getIdDegree() {
		return idDegree;
	}

	public void setIdDegree(int idDegree) {
		this.idDegree = idDegree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDegree;
		result = prime * result + idEstablishment;
		result = prime * result + idResume;
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
		DegreePK other = (DegreePK) obj;
		if (idDegree != other.idDegree)
			return false;
		if (idEstablishment != other.idEstablishment)
			return false;
		if (idResume != other.idResume)
			return false;
		return true;
	}

}
