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

@Entity
@Table(name="answered_surverys")
public class AnsweredSurvey implements Serializable {
	private static final long serialVersionUID = -6643183558907253725L;

	@Id @GeneratedValue @Column(nullable=false, unique=true, name="answered_survery_id") 
	private Long answeredSurveyId;
	
	@Version
	@Column(name="date")
	private Date date;
	
	@ManyToOne()
	@JoinColumn(name="patient_id", nullable=false)
	private PatientData patientData;
	
	@Column(nullable=false, name="reffered_by")
	private String refferedBy;
	
	
	@Column(nullable=false, name="medical_problem")
	private String medicalProblem;
	
	public AnsweredSurvey() {		
	}

	public AnsweredSurvey(Date date, PatientData patientData,
			String refferedBy, String medicalProblem) {
		super();
		this.date = date;
		this.patientData = patientData;
		this.refferedBy = refferedBy;
		this.medicalProblem = medicalProblem;
	}


	public AnsweredSurvey(Long answeredSurveyId, Date date,
			PatientData patientData, String refferedBy, String medicalProblem) {
		this.answeredSurveyId = answeredSurveyId;
		this.date = date;
		this.patientData = patientData;
		this.refferedBy = refferedBy;
		this.medicalProblem = medicalProblem;
	}


	public String getRefferedBy() {
		return refferedBy;
	}

	public void setRefferedBy(String refferedBy) {
		this.refferedBy = refferedBy;
	}

	public String getMedicalProblem() {
		return medicalProblem;
	}

	public void setMedicalProblem(String medicalProblem) {
		this.medicalProblem = medicalProblem;
	}

	public void setPatientData(PatientData patientData) {
		this.patientData = patientData;
	}

	public Long getAnsweredSurveyId() {
		return answeredSurveyId;
	}

	public void setAnsweredSurveyId(Long answeredSurveyId) {
		this.answeredSurveyId = answeredSurveyId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PatientData getPatientData() {
		return patientData;
	}

	public void setPatientId(PatientData patientData) {
		this.patientData = patientData;
	}		
}
