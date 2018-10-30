package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Competence  implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompetence;
	private String Label;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Level> levels;
	private String description;
	@ManyToMany(mappedBy="competences",fetch = FetchType.EAGER)
	private List<ProjectRequest> projectRequests;
	
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}	
	public List<ProjectRequest> getProjectRequests() {
		return projectRequests;
	}
	public void setProjectRequests(List<ProjectRequest> projectRequests) 
	{
		this.projectRequests = projectRequests;
	}
	
	public List<Level> getLevels() {
		return levels;
	}
	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	public int getIdCompetence() {
		return idCompetence;
	}
	public void setIdCompetence(int idCompetence) {
		this.idCompetence = idCompetence;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}

}
