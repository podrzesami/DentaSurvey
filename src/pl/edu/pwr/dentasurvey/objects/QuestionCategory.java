package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="question_categories") @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class QuestionCategory implements Serializable{
	private static final long serialVersionUID = 2004912436434381228L;
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(nullable=false, unique=true, name="question_category_id")
	private Long questionCategoryId;
	
	@Column(nullable=false, unique=true, name="category")
	private String category;

	public QuestionCategory(String category) {
		this.category = category;
	}	
}
