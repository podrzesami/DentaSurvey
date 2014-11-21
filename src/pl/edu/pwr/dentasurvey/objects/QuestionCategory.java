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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="question_categories") @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class QuestionCategory implements Serializable{
	private static final long serialVersionUID = 2004912436434381228L;
	
	@Id
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="question_category_id")
	private Long questionCategoryId;
	
	@Column(nullable=false, unique=true, name="category")
	private String category;
	
	@OneToMany(mappedBy="questionCategory", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Question> questions;

	public QuestionCategory(Long questionCategoryId, String category) {
		this.questionCategoryId = questionCategoryId;
		this.category = category;
	}

	public QuestionCategory(String category) {
		this.category = category;
	}	
}
