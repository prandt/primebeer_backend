package br.com.primebeer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primebeer.model.TypeBeer;
import br.com.primebeer.repository.TypeBeerRepository;

@Service
public class TypeBeerService {
	
	@Autowired
	private TypeBeerRepository repo;
	
	public List<TypeBeer> find(){
		List<TypeBeer> list = Optional.ofNullable(repo.findAll()).orElse(null);
		return list;
	}
	
	public TypeBeer findById(Integer id) {
		TypeBeer typeBeer = Optional.ofNullable(repo.findById(id)).orElse(null).orElseThrow();
		return typeBeer;
	}
	
	public TypeBeer save(TypeBeer obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public void update(TypeBeer typeBeer) {
		findById(typeBeer.getId());
		repo.save(typeBeer);
	}
	
}
