package entities;

import java.io.Serializable;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enums.Availability;
import enums.ContractType;

@Entity
public class Resource extends User implements Serializable {

	private String firstName;
	private String lastName;
	private String photo;
	private Double rate;
	
	@Enumerated(EnumType.STRING)
	private ContractType contractType;
	@ManyToOne
	private Seniority seniority;
	private boolean state;
	@Enumerated(EnumType.STRING)
	private Availability availability;
	@OneToOne
	private Resume resume;
	@OneToMany(mappedBy = "resource" , fetch = FetchType.EAGER )
	private List<DayOff> dayOffs;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Note> notes;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Holidays> holidays;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Level> levels;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Term> terms;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<LeaveRequest> leaveRequests;
	@ManyToMany(mappedBy = "resources",fetch = FetchType.EAGER)
	private Set<Field> fields;

	public Set<LeaveRequest> getLeaveRequests() {
		return leaveRequests;
	}

	public void setLeaveRequests(Set<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}

	public Set<Term> getTerms() {
		return terms;
	}

	public void setTerms(Set<Term> terms) {
		this.terms = terms;
	}

	public Set<Level> getLevels() {
		return levels;
	}

	public void setLevels(Set<Level> levels) {
		this.levels = levels;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public Seniority getSeniority() {
		return seniority;
	}

	public void setSeniority(Seniority seniority) {
		this.seniority = seniority;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public List<DayOff> getLeaves() {
		return dayOffs;
	}

	public void setLeaves(List<DayOff> dayOffs) {
		this.dayOffs = dayOffs;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Set<Holidays> getHolidays() {
		return holidays;
	}

	public void setHolidays(Set<Holidays> holidays) {
		this.holidays = holidays;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public List<DayOff> getDayOffs() {
		return dayOffs;
	}

	public void setDayOffs(List<DayOff> dayOffs) {
		this.dayOffs = dayOffs;
	}

	public Set<Field> getFields() {
		return fields;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

}
