package br.com.primebeer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primebeer.model.Volume;
import br.com.primebeer.repository.VolumeRepository;

@Service
public class VolumeService {
	
	@Autowired
	private VolumeRepository repo;
	
	public List<Volume> find(){
		List<Volume> list = Optional.ofNullable(repo.findAll()).orElse(null);
		return list;
	}
	
	public Volume findById(Integer id) {
		Volume volume = Optional.ofNullable(repo.findById(id)).orElse(null).orElseThrow();
		return volume;
	}
	
	public Volume save(Volume obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public void update(Volume volume) {
		findById(volume.getId());
		repo.save(volume);
	}
}
