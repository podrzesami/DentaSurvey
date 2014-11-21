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
@Table(name="languages") @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Language implements Serializable{
	private static final long serialVersionUID = -3797547325568861174L;
	
	@Id 
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="language_id")
	private Long languageId;
	
	@Column(nullable=false, unique=true, name="language")
	private String language;
	
	@OneToMany(mappedBy="language", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Survey> surveys;

	public Language(Long languageId, String language) {
		super();
		this.languageId = languageId;
		this.language = language;
	}

	public Language(String language) {
		super();
		this.language = language;
	}
}
