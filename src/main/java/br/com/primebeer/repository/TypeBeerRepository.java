package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.TypeBeer;

@Repository
public interface TypeBeerRepository extends JpaRepository<TypeBeer, Integer>{

}
