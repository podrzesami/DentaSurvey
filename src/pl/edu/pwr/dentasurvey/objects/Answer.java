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

@Entity
@Table(name="answers") @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Answer implements Serializable{
	private static final long serialVersionUID = -7887861321267745750L;
	
	@Id 
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="answer_id")
	private Long answerId;
	
	@Column(nullable=false, name="answer")
	private String answer;
	
	@ManyToOne()
	@JoinColumn(name="question_id", nullable=false)
	private Question question;
	
	@ManyToOne()
	@JoinColumn(nullable=false, name="answered_survey_id")
	private AnsweredSurvey answeredSurvey;
	
	public Answer(String answer, Question question,
			AnsweredSurvey answeredSurvey) {
		this.answer = answer;
		this.question = question;
		this.answeredSurvey = answeredSurvey;
	}
}
