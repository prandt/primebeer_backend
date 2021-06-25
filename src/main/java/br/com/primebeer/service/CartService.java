package br.com.primebeer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.primebeer.model.Cart;
import br.com.primebeer.model.ItemCart;
import br.com.primebeer.model.User;
import br.com.primebeer.repository.CartRepository;
import br.com.primebeer.repository.ItemCartRepository;
import br.com.primebeer.security.UserSS;
import br.com.primebeer.service.exceptions.AuthorizationException;

@Service
public class CartService {

	@Autowired
	private CartRepository repo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BeerService beerService;
	
	@Autowired
	private ItemCartRepository itemCartRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	public List<Cart> find(){
		List<Cart> list = Optional.ofNullable(repo.findAll()).orElse(null);
		return list;
	}
	
	public Cart save(Cart cart) {
		cart.setId(null);
		cart.setInstant(new Date());
		cart.setUser(userService.findById(cart.getUser().getId()));
		cart = repo.save(cart);
		for(ItemCart ic : cart.getItems()) {
			ic.setDiscount(0.0);
			ic.setBeer(beerService.findById(ic.getBeer().getId()));
			ic.setPrice(ic.getBeer().getPrice());
			ic.setCart(cart);
		}
		itemCartRepository.saveAll(cart.getItems());
		emailService.sendeOrderConfirmationEmailHTML(cart);
		return cart;
	}
	
	public Cart findById(Integer id) {
		Cart cart = Optional.ofNullable(repo.findById(id)).orElse(null).orElseThrow();
		return cart;
	}
	
	public Page<Cart> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserServiceO.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso nego");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		User cliente = userService.findById(user.getId());
		return repo.findByUser(cliente, pageRequest);
	}
	
}
