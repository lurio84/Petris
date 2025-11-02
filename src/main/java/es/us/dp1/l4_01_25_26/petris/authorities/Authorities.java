package es.us.dp1.l4_01_25_26.petris.authorities;

import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity{
	
	@Column(length = 30, unique = true) 
	@NotBlank(message = "Authority can't be blank") 
	@Size(max = 30, message = "Authority must have at most 30 characters") 
	private String authority="PLAYER"; 
	
}
