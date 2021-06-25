package br.com.primebeer.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.primebeer.dto.EmailUsedDTO;
import br.com.primebeer.dto.UserDTO;
import br.com.primebeer.dto.UserNewDTO;
import br.com.primebeer.model.User;
import br.com.primebeer.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> list(){
		List<User> list = service.find();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> save(@RequestBody UserNewDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserNewDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findbyId(@PathVariable Integer id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email){
		User user = service.findByEmail(email);
		return ResponseEntity.ok().body(user);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/emailvalidate/{email}")
	public ResponseEntity<EmailUsedDTO> emailIsUsed(@PathVariable String email){
		EmailUsedDTO obj = new EmailUsedDTO();
		obj.setEmail(email);
		obj.setUsed(service.emailsIsUsed(email));
		return ResponseEntity.ok().body(obj);
	} 
	
}
