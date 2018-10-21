package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.jar.Attributes.Name;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idUser;
	private String email;
	private String password;
	private String address;
	private String phoneNumber;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAuthentificated;
	@Temporal(TemporalType.TIMESTAMP)
	private Date passwordLastChanged;

	

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getLastAuthentificated() {
		return lastAuthentificated;
	}

	public void setLastAuthentificated(Date lastAuthentificated) {
		this.lastAuthentificated = lastAuthentificated;
	}

	public Date getPasswordLastChanged() {
		return passwordLastChanged;
	}

	public void setPasswordLastChanged(Date passwordLastChanged) {
		this.passwordLastChanged = passwordLastChanged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idUser;
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", password=" + password + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", lastAuthentificated=" + lastAuthentificated
				+ ", passwordLastChanged=" + passwordLastChanged + "]";
	}

}
