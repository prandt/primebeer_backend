package br.com.primebeer.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.primebeer.dto.UserNewDTO;
import br.com.primebeer.model.Address;
import br.com.primebeer.model.User;
import br.com.primebeer.model.enums.Profile;
import br.com.primebeer.repository.AddressRepository;
import br.com.primebeer.repository.UserRepository;
import br.com.primebeer.security.UserSS;
import br.com.primebeer.service.exceptions.AuthorizationException;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AddressRepository adRepo;
	
	
	public List<User> find(){
		
		List<User> list = Optional.ofNullable(repo.findAll()).orElse(null);
		return list;
	}
	
	public boolean emailsIsUsed(String email) {
		if(repo.findByEmail(email) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public User findById(Integer id) {
		UserSS user = UserServiceO.authenticated();
		if(user == null || !user.hasHole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		User obj = Optional.ofNullable(repo.findById(id).orElse(null)).orElseThrow();
		return obj;
	}
	
	@Transactional
	public User save(User obj) {
		obj.setId(null);
		obj = repo.save(obj);
		adRepo.saveAll(obj.getAddresses());
		return obj;
	}
	public User update(User obj) {
		findById(obj.getId());
		return repo.save(obj);	
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User findByEmail(String email) {
		User user = Optional.ofNullable(repo.findByEmail(email)).orElse(null);
		return user;
	}
	
	public User fromDTO(UserNewDTO objDTO) {
		User user = new User(null, objDTO.getName(), objDTO.getEmail(), pe.encode(objDTO.getPassword()), objDTO.getBirthDate());
		Address address = new Address(null, objDTO.getStreet(), objDTO.getCity(), objDTO.getState(), objDTO.getPostalCode(), user);
		user.getAddresses().add(address);
		user.getPhoneNumbers().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			user.getPhoneNumbers().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			user.getPhoneNumbers().add(objDTO.getTelefone3());
		}
		return user;
	}
}
