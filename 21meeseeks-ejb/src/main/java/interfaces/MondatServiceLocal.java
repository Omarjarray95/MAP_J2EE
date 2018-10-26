package interfaces;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.ejb.Local;

import entities.Competence;
import entities.Resource;
import entities.Term;
import enums.Availability;

@Local
public interface MondatServiceLocal {
	public List<Resource> findResourceByCompetenceAndAvailability(Competence competence,
			EnumSet<Availability> availables);

	public void addTerm(Term term);
	public Date calculateEndDateTerm(Term term);

}
