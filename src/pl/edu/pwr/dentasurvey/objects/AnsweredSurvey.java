package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="answered_surveys") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AnsweredSurvey implements Serializable {
	private static final long serialVersionUID = -6643183558907253725L;

	@Id
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="answered_survey_id") 
	private Long answeredSurveyId;
	
	@Version
	@Column(name="date")
	private Date date;
	
	@ManyToOne()
	@JoinColumn(name="patient_id", nullable=false)
	private PatientData patientData;
	
	@Column(nullable=false, name="referred_by")
	private String refferedBy;	
	
	@Column(nullable=false, name="medical_problem")
	private String medicalProblem;

	public AnsweredSurvey(Date date, PatientData patientData,
			String refferedBy, String medicalProblem) {
		super();
		this.date = date;
		this.patientData = patientData;
		this.refferedBy = refferedBy;
		this.medicalProblem = medicalProblem;
	}		
}
