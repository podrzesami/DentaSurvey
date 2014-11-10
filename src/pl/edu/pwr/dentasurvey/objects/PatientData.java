package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="patients_data")
public class PatientData implements Serializable {
	private static final long serialVersionUID = -7502796570083370647L;

	@Id @Column(nullable=false, unique=true, name="patient_id")
	private Long patientId;
	
	@Column(nullable=false, name="name")
	private String name;
	
	@Column(nullable=false, name="surname")
	private String surname;
	
	@Column(nullable=false, name="age")
	private int age;
	
	@Column(nullable=false, name="address")
	private String address;
	
	@Column(nullable=false, name="phone_number")
	private String phoneNumber;
	
	@Column(nullable=false, name="number_of_kids")
	private int numberOfKids;
	
	@Column(nullable=false, name="occupation")
	private String occupation;
	
	@OneToMany(mappedBy="patientData", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<AnsweredSurvey> answeredSurveys;	

	public PatientData() {
	}
	
	public PatientData(Long patientId, String name, String surname, int age,
			String address, String phoneNumber, int numberOfKids,
			String occupation, List<AnsweredSurvey> answeredSurveys) {
		this.patientId = patientId;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.numberOfKids = numberOfKids;
		this.occupation = occupation;
		this.answeredSurveys = answeredSurveys;
	}

	public PatientData(Long patientId, String name, String surname, int age,
			String address, String phoneNumber, int numberOfKids,
			String occupation) {
		this.patientId = patientId;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.numberOfKids = numberOfKids;
		this.occupation = occupation;
	}
	
	public PatientData(String name, String surname, int age,
			String address, String phoneNumber, int numberOfKids,
			String occupation) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.numberOfKids = numberOfKids;
		this.occupation = occupation;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public int getNumberOfKids() {
		return numberOfKids;
	}
	public void setNumberOfKids(int numberOfKids) {
		this.numberOfKids = numberOfKids;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public List<AnsweredSurvey> getAnsweredSurveys() {
		return answeredSurveys;
	}

	public void setAnsweredSurveys(List<AnsweredSurvey> answeredSurveys) {
		this.answeredSurveys = answeredSurveys;
	}	
}
