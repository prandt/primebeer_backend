package br.com.primebeer.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.primebeer.model.TypeBeer;
import br.com.primebeer.service.TypeBeerService;

@RestController
@RequestMapping("/typebeers")
public class TypeBeerController {
	
	@Autowired
	private TypeBeerService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TypeBeer> save(@RequestBody TypeBeer obj){
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TypeBeer>> find(){
		List<TypeBeer> list = service.find();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<TypeBeer> findById(@PathVariable Integer id){
		TypeBeer obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
