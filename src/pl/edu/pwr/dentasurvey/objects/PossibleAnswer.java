package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="possible_answers") @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class PossibleAnswer implements Serializable{
	private static final long serialVersionUID = 2594527037558301835L;

	@Id 
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(nullable=false, unique=true, name="possible_answer_id")
	private Long possibleAnswerId;
	
	@Column(nullable=false, unique=false, name="possible_answer")
	private String possible_answer;
	
	@ManyToOne()
	@JoinColumn(name="question_type_id", nullable=false)
	private QuestionType questionType;

	public PossibleAnswer(Long possibleAnswerId, String possible_answer) {
		super();
		this.possibleAnswerId = possibleAnswerId;
		this.possible_answer = possible_answer;
	}	
}
