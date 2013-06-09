package com.impromptu.security;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.impromptu.users.User;
import com.impromptu.users.UserRepository;

/**
 * Authenticates users from the {@link UserRepository}.
 * 
 * @author Tim Tsu
 *
 */
@Service
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final UserRepository accountRepository;
	
	@Autowired
	public UsernamePasswordAuthenticationProvider(UserRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();
		String password = (String)token.getCredentials();
		User account = accountRepository.findByEmailAndPassword(username, password);
		if (account == null) {
			logger.info("Invalid login. username={} password={}", username, password);
			throw new BadCredentialsException("Bad credentials");
		}
		return authenticatedToken(account, authentication);
	}

	private Authentication authenticatedToken(User account, Authentication original) {
		UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(account, null, 
				Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
		authenticated.setDetails(original.getDetails());
		return authenticated;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
}
