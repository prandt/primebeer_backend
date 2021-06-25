package br.com.primebeer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primebeer.model.Manufacturer;
import br.com.primebeer.repository.ManufacturerRepository;

@Service
public class ManufacturerService {
	
	@Autowired
	private ManufacturerRepository repo;
	
	public List<Manufacturer> find(){
		List<Manufacturer> list = Optional.ofNullable(repo.findAll()).orElse(null);
		return list;
	}
	
	public Manufacturer findById(Integer id) {
		Manufacturer manufacturer = Optional.ofNullable(repo.findById(id)).orElse(null).orElseThrow();
		return manufacturer;
	}
	
	public Manufacturer save(Manufacturer obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public void update(Manufacturer manufacturer) {
		findById(manufacturer.getId());
		repo.save(manufacturer);
	}
	
}
