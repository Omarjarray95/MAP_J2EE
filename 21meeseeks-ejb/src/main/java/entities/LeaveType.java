package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class LeaveType implements Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLeaveType;
	private String name;
	private String description;
	
	public int getIdLeaveType() {
		return idLeaveType;
	}
	public void setIdLeaveType(int idLeaveType) {
		this.idLeaveType = idLeaveType;
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
	
	

}
