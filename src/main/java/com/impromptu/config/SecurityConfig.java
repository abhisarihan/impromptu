package com.impromptu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:com/impromptu/config/security.xml")
public class SecurityConfig {

}
