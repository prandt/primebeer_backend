package br.com.primebeer.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.primebeer.model.Cart;
import br.com.primebeer.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	private CartService service;
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Cart>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "instant")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC")String direction){
		Page<Cart> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cart> save(@RequestBody Cart obj){
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Cart> findById(@PathVariable Integer id){
		Cart cart = service.findById(id);
		return ResponseEntity.ok().body(cart);
	}

}
