package es.us.dp1.l4_01_25_26.petris.user.authorities;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends  CrudRepository<Authorities, Integer>{
	
	@Query("SELECT DISTINCT auth FROM Authorities auth WHERE auth.authority LIKE :authority%")
	Optional<Authorities> findByName(String authority);
	
}
