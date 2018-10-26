package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Establishment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstablishment;
	private String nameEstablishment;
	
	@OneToMany(mappedBy = "establishment", cascade= CascadeType.PERSIST)
	private List<Degree> degreeList = new ArrayList<>();
	
	public void addDegree(Degree dg){
		dg.setEstablishment(this);
		this.degreeList.add(dg);
	}

	public int getIdEstablishment() {
		return idEstablishment;
	}

	public void setIdEstablishment(int idEstablishment) {
		this.idEstablishment = idEstablishment;
	}

	public String getNameEstablishment() {
		return nameEstablishment;
	}

	public void setNameEstablishment(String nameEstablishment) {
		this.nameEstablishment = nameEstablishment;
	}

	public List<Degree> getDegreeList() {
		return degreeList;
	}

	public void setDegreeList(List<Degree> degreeList) {
		this.degreeList = degreeList;
	}

	
}
