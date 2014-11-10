package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="languages")
public class Language implements Serializable{
	private static final long serialVersionUID = -3797547325568861174L;
	
	@Id @GeneratedValue @Column(nullable=false, unique=true, name="language_id")
	private Long languageId;
	
	@Column(nullable=false, unique=true, name="language")
	private String language;
	
	@OneToMany(mappedBy="language", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Survey> surveys;
	
	Language(){		
	}

	public Language(Long languageId, String language) {
		super();
		this.languageId = languageId;
		this.language = language;
	}

	public Language(String language) {
		super();
		this.language = language;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}	
}
