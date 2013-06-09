package com.impromptu.users;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolves a User method parameter as the principal of the currently authenticated user.
 * If no user is currently authenticated, the argument is resolved as <code>null</code>.
 * 
 * @author Tim Tsu
 *
 */
public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return User.class.equals(parameter.getParameterType()) && parameter.hasParameterAnnotation(Authenticated.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, 
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		
		return userAccountOf(authentication);
	}
	
	private User userAccountOf(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof User) {
			return (User) authentication.getPrincipal();
		}
		
		return null;
	}

}
