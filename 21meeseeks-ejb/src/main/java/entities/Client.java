package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.ClientType;

@Entity
public class Client extends User implements Serializable {

	private String clientName;
	private String logo;
	@Enumerated(EnumType.STRING)
	private ClientType clientType;
	@ManyToOne(fetch=FetchType.EAGER)
	private ClientCategory clientCategory;
	@OneToMany(mappedBy = "client"
			,fetch=FetchType.EAGER)
	private List<Organigram> organigrams;
	@OneToMany(mappedBy = "client"
			,fetch=FetchType.EAGER)
	private List<Note> notes;
	@OneToMany(mappedBy = "client"
			,fetch=FetchType.EAGER)
	private List<ProjectRequest> projectRequests;
	
	
	@Override
	public String toString() {
		return "Client [clientName=" + clientName + ", logo=" + logo
				+ ", clientType=" + clientType + ", clientCategory="
				+ clientCategory + ", organigrams=" + organigrams + ", notes="
				+ notes + ", projectRequests=" + projectRequests + "]";
	}

	public List<ProjectRequest> getProjectRequests() {
		return projectRequests;
	}

	public void setProjectRequests(List<ProjectRequest> projectRequests) {
		this.projectRequests = projectRequests;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public enums.ClientType getClientType() {
		return clientType;
	}

	public void setClientType(enums.ClientType clientType) {
		this.clientType = clientType;
	}

	public ClientCategory getClientCategory() {
		return clientCategory;
	}

	public void setClientCategory(ClientCategory clientCategory) {
		this.clientCategory = clientCategory;
	}

	public List<Organigram> getOrganigrams() {
		return organigrams;
	}

	public void setOrganigrams(List<Organigram> organigrams) {
		this.organigrams = organigrams;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}