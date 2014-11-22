package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="patients_data") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
