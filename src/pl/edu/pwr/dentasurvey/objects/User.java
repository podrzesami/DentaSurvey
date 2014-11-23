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
@Table(name="users") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1365940855814137208L;

	@Id 
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(nullable=false, unique=true, name="user_id")
	private Long userId;
	
	@Column(nullable=false, unique=true, name="username")
	private String username;
	
	@Column(nullable=false, name="password")
	private String password;
	
	@ManyToOne()
	@JoinColumn(nullable=false, name="role_id")
	private Role role;

	public User(Long userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}	
}
