package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Certificate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCertificate;
	private String descriptionCertificate;
	@ManyToMany(mappedBy = "certificates")
	private List<Resume> resume;

	public int getIdCertificate() {
		return idCertificate;
	}

	public void setIdCertificate(int idCertificate) {
		this.idCertificate = idCertificate;
	}

	public String getDescriptionCertificate() {
		return descriptionCertificate;
	}

	public void setDescriptionCertificate(String descriptionCertificate) {
		this.descriptionCertificate = descriptionCertificate;
	}

}
