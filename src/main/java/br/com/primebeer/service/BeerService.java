package br.com.primebeer.service;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.primebeer.dto.BeerNewDTO;
import br.com.primebeer.model.Beer;
import br.com.primebeer.model.Manufacturer;
import br.com.primebeer.model.Photo;
import br.com.primebeer.model.TypeBeer;
import br.com.primebeer.model.Volume;
import br.com.primebeer.repository.BeerRepository;
import br.com.primebeer.repository.PhotoRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository repo;
	
	@Autowired
	private PhotoRepository photoRepo;
	
	@Autowired
	private S3Service s3Service;
	
	
	public List<Beer> find(){
		List<Beer> list = Optional.ofNullable(repo.findAll()).orElse(null);
		return list;
	}
	
	public List<Beer> findByName(String name){
		List<Beer> list = Optional.ofNullable(repo.findByName(name)).orElse(null);
		return list;
	}
	
	public Beer findById(Integer id) {
		Beer beer = Optional.ofNullable(repo.findById(id)).orElse(null).orElseThrow();
		return beer;
	}
	
	@Transactional
	public Beer save(Beer obj) throws SQLIntegrityConstraintViolationException {
		obj.setId(null);
		obj = repo.save(obj);
		photoRepo.saveAll(obj.getPhotos());
		return obj;
	}
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public void update(Beer beer) {
		findById(beer.getId());
		repo.save(beer);
	}
	
	public Page<Beer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public URI uploadPhoto(MultipartFile multipartFile ) {
		return s3Service.uploadFile(multipartFile);
	}
	
	public Beer fromDTO(BeerNewDTO objDTO) {
		Manufacturer manu = new Manufacturer(objDTO.getManufactureId(), null, null);
		Volume volume = new Volume(objDTO.getVolumeId(), null);
		TypeBeer typeBeer = new TypeBeer(objDTO.getTypeBeerId(), null);
		Beer beer = new Beer
				(null, objDTO.getName(), objDTO.getPrice(), objDTO.getTeorAlcool(), 
				objDTO.getColoring(), objDTO.getAddtionalInformation(), objDTO.getRating(), objDTO.getAmount(), manu, typeBeer, volume);
		
		if(objDTO.getPhotoURL1() != null) {
			Photo photo = new Photo(null,objDTO.getPhotoURL1(),beer);
			beer.getPhotos().add(photo);
		}
		if(objDTO.getPhotoURL2() != null) {
			Photo photo = new Photo(null,objDTO.getPhotoURL2(),beer);
			beer.getPhotos().add(photo);
		}
		if(objDTO.getPhotoURL3() != null) {
			Photo photo = new Photo(null,objDTO.getPhotoURL3(),beer);
			beer.getPhotos().add(photo);
		}
		
		return beer;
	}
}
