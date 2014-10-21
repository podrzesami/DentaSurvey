package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="answer")
public class Answer implements Serializable{
	private static final long serialVersionUID = -7887861321267745750L;
	
	@Id @GeneratedValue @Column(nullable=false, unique=true, name="answer_id")
	private Long answerId;
	
	@Column(nullable=false, name="answer")
	private String answer;
	
	@ManyToOne()
	@JoinColumn(name="question_id")
	private Question question;
	
	@Column(name="old_question")
	private String oldQuestion;
	
	@ManyToOne()
	@JoinColumn(nullable=false, name="answered_survey_id")
	private AnsweredSurvey answeredSurvey;
	
	Answer() {		
	}

	public Answer(String answer, Question question, String oldQuestion,
			AnsweredSurvey answeredSurvey) {
		this.answer = answer;
		this.question = question;
		this.oldQuestion = oldQuestion;
		this.answeredSurvey = answeredSurvey;
	}

	public Answer(Long answerId, String answer, Question question,
			String oldQuestion, AnsweredSurvey answeredSurvey) {
		super();
		this.answerId = answerId;
		this.answer = answer;
		this.question = question;
		this.oldQuestion = oldQuestion;
		this.answeredSurvey = answeredSurvey;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getOldQuestion() {
		return oldQuestion;
	}

	public void setOldQuestion(String oldQuestion) {
		this.oldQuestion = oldQuestion;
	}

	public AnsweredSurvey getAnsweredSurvey() {
		return answeredSurvey;
	}

	public void setAnsweredSurvey(AnsweredSurvey answeredSurvey) {
		this.answeredSurvey = answeredSurvey;
	}	
}
