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
@Table(name="questions") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Question implements Serializable{
	private static final long serialVersionUID = -8547454023833683858L;
	
	@Id 
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="question_id")
	private Long questionId;
	
	@Column(nullable=false, name="question")
	private String question;
	
	@ManyToOne()
	@JoinColumn(name="question_type_id", nullable=false)
	private QuestionType questionType;
	
	@ManyToOne()
	@JoinColumn(name="survey_id", nullable=true)
	private Survey survey;

	@ManyToOne()
	@JoinColumn(name="question_category_id", nullable=false)
	private QuestionCategory questionCategory;

	public Question(String question, QuestionType questionType, Survey survey,
			QuestionCategory questionCategory) {
		this.question = question;
		this.questionType = questionType;
		this.survey = survey;
		this.questionCategory = questionCategory;
	}	
}
