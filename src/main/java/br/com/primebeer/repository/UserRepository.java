package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
