package br.com.primebeer.controller;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.primebeer.service.BeerService;
import br.com.primebeer.dto.BeerDTO;
import br.com.primebeer.dto.BeerNewDTO;
import br.com.primebeer.model.Beer;

@RestController
@RequestMapping("/beers")
public class BeerController {
	
	@Autowired
	private BeerService service;
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BeerDTO>> find(){
		List<Beer> list = service.find();
		List<BeerDTO> listDTO = list.stream().map(obj -> new BeerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/name/{name}")
	public ResponseEntity<List<BeerDTO>> findByName(@PathVariable String name){
		List<Beer> list = service.findByName(name);
		List<BeerDTO> listDTO = list.stream().map(obj -> new BeerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Beer> save(@RequestBody BeerNewDTO objDTO) throws SQLIntegrityConstraintViolationException{
		Beer obj = service.fromDTO(objDTO);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/photo")
	public ResponseEntity<Beer> uploadPhoto(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadPhoto(file);
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<BeerDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "price")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Beer> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<BeerDTO> listDTO = list.map(obj -> new BeerDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Beer> findById(@PathVariable Integer id){
		Beer beer = service.findById(id);
		return ResponseEntity.ok().body(beer);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Beer beer) {
		service.update(beer);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
