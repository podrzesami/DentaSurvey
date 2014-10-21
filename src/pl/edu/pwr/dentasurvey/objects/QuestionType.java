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
@Table(name="question_type")
public class QuestionType implements Serializable{
	private static final long serialVersionUID = -1592926327907327945L;

	@Id @GeneratedValue @Column(nullable=false, unique=true, name="question_type_id")
	private Long questionTypeId;

	@Column(nullable=false, unique=true, name="type")
	private String type;

	@OneToMany(mappedBy="questionType", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Question> questions;	
	
	public QuestionType() {		
	}
	
	public QuestionType(String type) {
		this.type=type;
	}

	public QuestionType(Long questionTypeId, String type) {
		this.questionTypeId = questionTypeId;
		this.type = type;
	}

	public Long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}		
}
