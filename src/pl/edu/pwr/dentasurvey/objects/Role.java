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
@Table(name="roles") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = -4099333026951641997L;

	@Id
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="role_id")
	private Long roleId;
	
	@Column(nullable=false, unique=true, name="role")
	private String role;
}
