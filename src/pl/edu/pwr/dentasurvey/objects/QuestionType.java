package pl.edu.pwr.dentasurvey.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="question_types") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuestionType implements Serializable{
	private static final long serialVersionUID = -1592926327907327945L;

	@Id 
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="question_type_id")
	private Long questionTypeId;

	@Column(nullable=false, unique=true, name="type")
	private String type;
		
	public QuestionType(String type) {
		this.type=type;
	}
}
