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
@Table(name="roles") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = -4099333026951641997L;

	@Id
	@GeneratedValue()
	@Column(nullable=false, unique=true, name="role_id")
	private Long roleId;
	
	@Column(nullable=false, unique=true, name="role")
	private String role;
	
	@OneToMany(mappedBy="role", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<User> users;	

	public Role(Long roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}
}
