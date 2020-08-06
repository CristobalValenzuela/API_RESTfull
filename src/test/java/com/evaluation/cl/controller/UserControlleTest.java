package com.evaluation.cl.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.evaluacion.cl.App;
import com.evaluacion.cl.repository.UserRepository;
import com.evaluacion.cl.to.Phone;
import com.evaluacion.cl.to.User;
import com.evaluacion.cl.util.Token;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = App.class)
public class UserControlleTest {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private Token token;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createNewUser() throws Exception {

		User user = new User();
		user.setName("Prueba");
		user.setPassword("Prueba12");
		user.setEmail("prueba@prueba.cl");

		List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setCitycode(2);
		phone.setContrycode(52);
		phone.setNumber(12345678);

		phones.add(phone);
		user.setPhones(phones);

		ResponseEntity<User> superHeroResponse = restTemplate.postForEntity("/user/", user, User.class);

		// then
		assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
