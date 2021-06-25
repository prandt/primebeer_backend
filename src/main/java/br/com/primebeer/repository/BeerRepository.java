package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer>{
	
}
