package br.com.primebeer.service;



import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.primebeer.security.UserSS;

public class UserServiceO {
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public static Collection<SimpleGrantedAuthority> getAuthorities() {
		try {
			Collection<SimpleGrantedAuthority> authorities = 
					(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			return authorities;
		}catch (Exception e) {
			return null;
		}
	}
}
