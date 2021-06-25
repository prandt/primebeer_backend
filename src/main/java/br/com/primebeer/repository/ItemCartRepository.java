package br.com.primebeer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primebeer.model.ItemCart;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Integer>{

}
