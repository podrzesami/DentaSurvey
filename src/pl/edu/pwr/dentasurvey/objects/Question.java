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
@Table(name="question")
public class Question implements Serializable{
	private static final long serialVersionUID = -8547454023833683858L;
	
	@Id @GeneratedValue @Column(nullable=false, unique=true, name="question_id")
	private Long questionId;
	
	@Column(nullable=false, name="question")
	private String question;
	
	@ManyToOne()
	@JoinColumn(name="question_type_id", nullable=false)
	private QuestionType questionType;
	
	@ManyToOne()
	@JoinColumn(name="survey_id", nullable=false)
	private Survey survey;

	@ManyToOne()
	@JoinColumn(name="question_category_id", nullable=false)
	private QuestionCategory questionCategory;
	
	@OneToMany(mappedBy="question", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	private List<Answer> answers;	
	
	Question() {
	}

	public Question(Long questionId, String question,
			QuestionType questionType, Survey survey,
			QuestionCategory questionCategory) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.questionType = questionType;
		this.survey = survey;
		this.questionCategory = questionCategory;
	}

	public Question(String question, QuestionType questionType, Survey survey,
			QuestionCategory questionCategory) {
		super();
		this.question = question;
		this.questionType = questionType;
		this.survey = survey;
		this.questionCategory = questionCategory;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}	
}
