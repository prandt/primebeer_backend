package br.com.primebeer.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.Cart;
import br.com.primebeer.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Transactional
	Page<Cart> findByUser(User user, Pageable pageRequest);
	
}
