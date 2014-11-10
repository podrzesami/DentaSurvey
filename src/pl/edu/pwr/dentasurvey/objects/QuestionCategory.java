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
@Table(name="question_categories")
public class QuestionCategory implements Serializable{
	private static final long serialVersionUID = 2004912436434381228L;
	
	@Id @GeneratedValue @Column(nullable=false, unique=true, name="question_category_id")
	private Long questionCategoryId;
	
	@Column(nullable=false, unique=false, name="category")
	private String category;
	
	@OneToMany(mappedBy="questionCategory", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Question> questions;

	public QuestionCategory() {
		super();
	}

	public QuestionCategory(Long questionCategoryId, String category) {
		super();
		this.questionCategoryId = questionCategoryId;
		this.category = category;
	}

	public QuestionCategory(String category) {
		super();
		this.category = category;
	}

	public Long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(Long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}	
}
