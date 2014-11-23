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
@Table(name="languages") @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Language implements Serializable{
	private static final long serialVersionUID = -3797547325568861174L;
	
	@Id 
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(nullable=false, unique=true, name="language_id")
	private Long languageId;
	
	@Column(nullable=false, unique=true, name="language")
	private String language;	

}
