package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="surveys") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Survey implements Serializable{
	private static final long serialVersionUID = -6737763724946741575L;
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(nullable=false, unique=true, name="survey_id")
	private Long surveyId;
	
	@Column(nullable=false, unique=true, name="title")
	private String title;
	
	@ManyToOne()
	@JoinColumn(nullable=false, name="language_id")
	private Language language;	
	
	public Survey(String title, Language language) {
		super();
		this.title = title;
		this.language = language;
	}
}
