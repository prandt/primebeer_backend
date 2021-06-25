package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
