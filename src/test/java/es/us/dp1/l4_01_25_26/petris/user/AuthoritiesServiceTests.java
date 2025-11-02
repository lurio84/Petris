package es.us.dp1.l4_01_25_26.petris.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.l4_01_25_26.petris.exceptions.ResourceNotFoundException;
import es.us.dp1.l4_01_25_26.petris.user.Authorities;
import es.us.dp1.l4_01_25_26.petris.user.AuthoritiesService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;

@Epic("Users & Admin Module")
@Feature("Authorization")
@Owner("DP1-tutors")
@SpringBootTest
@AutoConfigureTestDatabase
class AuthoritiesServiceTests {

	// @Autowired
	// private UserService userService;

	@Autowired
	private AuthoritiesService authService;

	@Autowired
	private AuthoritiesRepository authRepository;
	//GET ALL
	@Test
	void shouldFindAllAuthorities() {
		List<Authorities> auths = (List<Authorities>) this.authService.findAll();
		assertEquals(2, auths.size());
	}
	//FIND BY NAME
	@Test
	void shouldFindAuthoritiesByAuthority() {
		Authorities auth = this.authService.findByAuthority("ADMIN");
		assertEquals("ADMIN", auth.getAuthority());
	}
	//FIND BY NAME (error case, not found)
	@Test
	void shouldNotFindAuthoritiesByIncorrectAuthority() {
		assertThrows(ResourceNotFoundException.class, () -> this.authService.findByAuthority("authnotexists"));
	}

	//CREATE
	@Test
	@Transactional
	void shouldInsertAuthorities() {
		int count = ((Collection<Authorities>) this.authService.findAll()).size();

		Authorities auth = new Authorities();
		auth.setAuthority("CLIENT");

		this.authService.saveAuthorities(auth);
		assertNotEquals(0, auth.getId().longValue());
		assertNotNull(auth.getId());

		int finalCount = ((Collection<Authorities>) this.authService.findAll()).size();
		assertEquals(count + 1, finalCount);
	}
	//CREATE (negative case, duplicate or size not between 1-30 )
	// @Test
	// @Transactional
	// void shouldNotInsertAuthorities() {

	// 	Authorities auth1 = new Authorities();
	// 	auth1.setAuthority("NEWROLE");
	// 	this.authService.saveAuthorities(auth1);

	// 	//######## Duplicated case, should throw DataIntegrityViolationException
	// 	Authorities auth2 = new Authorities();
	// 	auth2.setAuthority("NEWROLE");

	// 	boolean error = false;
	// 	try {
	// 		this.authService.saveAuthorities(auth2);
	// 	} catch (Exception e) {
	// 		error = true;
	// 		assertTrue(e instanceof DataIntegrityViolationException, "Duplicated authority");
	// 	}
	// 	assertTrue(error);

	// 	//######## Not duplicated case, should be created
	// 	Authorities auth3 = new Authorities();
	// 	auth3.setAuthority("NEWROL1");
	// 	this.authService.saveAuthorities(auth3);

	// 	boolean existsNewRol1 = ((Collection<Authorities>) this.authService.findAll()).stream()
	// 		.anyMatch(a -> a.getAuthority().equals("NEWROL1"));
	// 	assertTrue(existsNewRol1);

	// 	//######## NotBlank case
	// 	Authorities auth4 = new Authorities();
	// 	auth4.setAuthority(" "); // blank
	// 	Authorities auth5 = new Authorities();
	// 	auth5.setAuthority(""); // empty

	// 	this.authService.saveAuthorities(auth4);
	// 	this.authService.saveAuthorities(auth5);

	// 	boolean existsBlank = ((Collection<Authorities>) this.authService.findAll()).stream()
	// 		.anyMatch(a -> a.getAuthority().trim().isEmpty());
	// 	assertFalse(existsBlank, "You can't create blank authority");

	// 	//######## Size case >30
	// 	Authorities auth6 = new Authorities();
	// 	auth6.setAuthority("0123456789 0123456789 0123456789"); 
	// 	this.authService.saveAuthorities(auth6);

	// 	boolean existsLong = ((Collection<Authorities>) this.authService.findAll()).stream()
	// 		.anyMatch(a -> a.getAuthority().length() > 30);
	// 	assertFalse(existsLong, "Authority can't be longer than 30 characters");
	// }



	
}
