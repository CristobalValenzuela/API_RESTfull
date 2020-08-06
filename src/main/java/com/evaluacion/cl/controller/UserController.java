package com.evaluacion.cl.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.cl.repository.UserRepository;
import com.evaluacion.cl.to.User;
import com.evaluacion.cl.util.Token;

/**
 * Clase controller del objeto {@link User}
 * 
 * @author cvalenzm
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Token token;

	/**
	 * Metodo que retorna la lista de usuarios
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private List<User> usersList() {
		return userRepository.findAll();
	}

	/**
	 * Metodo que registra un nuevo usuario
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("codigo", HttpStatus.OK.value());
		
		if(userRepository.findByEmail(user.getEmail()) != null) {
			List<String> errors = new ArrayList<String>();
			errors.add("El correo ya registrado");
			body.put("codigo", HttpStatus.BAD_REQUEST.value());
			body.put("mensaje", errors);
			return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
		}
		
		user.setToken(token.getToken(user));
		user = userRepository.save(user);
		body.put("usuario", user);
		return new ResponseEntity<Object>(body, HttpStatus.OK);
	}
}
