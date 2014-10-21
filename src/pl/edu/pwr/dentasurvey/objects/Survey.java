package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="syrvey")
public class Survey implements Serializable{
	private static final long serialVersionUID = -6737763724946741575L;
	
	@Id @GeneratedValue @Column(nullable=false, unique=true, name="survey_id")
	private Long surveyId;
	
	@Column(nullable=false, unique=true, name="title")
	private String title;
	
	@ManyToOne()
	@JoinColumn(nullable=false, name="language_id")
	private Language language;
	
	@OneToMany(mappedBy="survery", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Question> questions;

	public Survey() {
	}

	public Survey(String title, Language language) {
		super();
		this.title = title;
		this.language = language;
	}

	public Survey(Long surveyId, String title, Language language) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.language = language;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	} 	
}
