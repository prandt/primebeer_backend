package br.com.primebeer;




import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import br.com.primebeer.model.Address;
import br.com.primebeer.model.Beer;
import br.com.primebeer.model.Cart;
import br.com.primebeer.model.ItemCart;
import br.com.primebeer.model.Manufacturer;
import br.com.primebeer.model.TypeBeer;
import br.com.primebeer.model.User;
import br.com.primebeer.model.Volume;
import br.com.primebeer.repository.AddressRepository;
import br.com.primebeer.repository.BeerRepository;
import br.com.primebeer.repository.CartRepository;
import br.com.primebeer.repository.ItemCartRepository;
import br.com.primebeer.repository.ManufacturerRepository;
import br.com.primebeer.repository.TypeBeerRepository;
import br.com.primebeer.repository.UserRepository;
import br.com.primebeer.repository.VolumeRepository;
import br.com.primebeer.model.enums.Profile;

@SpringBootApplication
public class PrimeBeerApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private TypeBeerRepository typeBeerRepository;
	@Autowired
	private VolumeRepository volumeRepository;
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ItemCartRepository itemCartRepository;
	@Autowired
	private BeerRepository beerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PrimeBeerApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
	/*	User u1 = new User(null,"Administrado","richard@gmail.com",pe.encode("1234"),null);
		Address ad = new Address(null,"Av Brasil","Contenda", "PR","1234",u1);
		u1.addPerfil(Profile.ADMIN);
		
		userRepository.save(u1);
		addressRepository.save(ad);
	*/
		
		/*TypeBeer typeBeer = new TypeBeer(null, "IPA");
		Volume volume = new Volume(null, "500ml");
		
		typeBeerRepository.save(typeBeer);
		volumeRepository.save(volume);
		
		Manufacturer manufacturer = new Manufacturer(null, "Empresa da dete", "Rua jose");
		manufacturerRepository.save(manufacturer);
		
		Beer beer = new Beer(null, "cerveja de teste", 10.5, 5.5, "Amarelo", null, null, 10, manufacturer, typeBeer, volume);
		beerRepository.save(beer);*/
	
		/*Cart cart = new Cart(null, null, u1, ad);
		
		cartRepository.save(cart);
		
		ItemCart itemCart = new ItemCart(cart, beer, 1.0, 1, 10.5);
		
		itemCartRepository.save(itemCart);*/
		
		
		
	}

}
