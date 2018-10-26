package entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@OneToMany(mappedBy = "resource")
	private List<DayOff> dayOffs;
	@OneToMany
	private List<Note> notes;
	@OneToMany
	private List<Holidays> holidays;
	@OneToMany
	private List<Level> levels;
	@OneToMany
	private List<Term> terms;
	@OneToMany
	private List<LeaveRequest> leaveRequests;
	@ManyToMany(mappedBy = "resources")
	private List<Field> fields;

	public List<LeaveRequest> getLeaveRequests() {
		return leaveRequests;
	}

	public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Holidays> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holidays> holidays) {
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

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
