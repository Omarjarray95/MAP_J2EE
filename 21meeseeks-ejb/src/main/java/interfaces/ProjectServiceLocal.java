package interfaces;

import java.util.List;

import javax.ejb.Local;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import entities.Client;
import entities.Competence;
import entities.Project;
import entities.ProjectRequest;

@Local
public interface ProjectServiceLocal {
public Project addProject(Project p);
public Project addProjectfromRequest(ProjectRequest request);
public List<Project> getAllProjects();
public String projectlisttoJson(List<Project> c) ;
public String  projectoJson(Project c) ;
public List<Competence> CompetencesSuggesionByProjectDesc(Project p);


}
