package com.evaluacion.cl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.cl.to.User;

/**
 * Interface que se utiliza para la persitencia del objecto User
 * @author cvalenzm
 *
 */
public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmail(String email);
}
