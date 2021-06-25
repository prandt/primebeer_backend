package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>{

}
