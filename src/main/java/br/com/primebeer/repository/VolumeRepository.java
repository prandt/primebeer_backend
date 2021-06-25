package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.Volume;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Integer>{

}
