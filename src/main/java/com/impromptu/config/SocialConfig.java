package com.impromptu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class SocialConfig {
	
	//FIXME: read this in
	private String facebookClientId = "209321265842875";
	private String facebookClientSecret = "e14d105e82eb8e8bf1fc9f4918ddb15a";

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
	    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
	    registry.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookClientSecret));
	    return registry;
	}
	
}
