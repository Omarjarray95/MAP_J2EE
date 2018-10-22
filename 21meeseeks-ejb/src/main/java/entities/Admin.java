package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
@Entity
public class Admin extends User implements Serializable{
	
	private String firstNam;
	private  String lastName;
	
	
	
	
	public String getFirstNam() {
		return firstNam;
	}
	public void setFirstNam(String firstName) {
		this.firstNam = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
