package es.us.dp1.l4_01_25_26.petris.user;

import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appusers")
public class User extends BaseEntity {

	@Column(unique = true)
	@NotBlank
	@Size(min=3 ,max = 30, message = "Username must have between 3-30 characters") 
	String username;
	
	@NotBlank
	@Size(min=6 ,max = 60, message = "Username must have between 6-60 characters") 
	String password;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "authority")
	Authorities authority;

	public Boolean hasAuthority(String auth) {
		return authority.getAuthority().equals(auth);
	}

	public Boolean hasAnyAuthority(String... authorities) {
		Boolean cond = false;
		for (String auth : authorities) {
			if (auth.equals(authority.getAuthority()))
				cond = true;
		}
		return cond;
	}

}
