package com.impromptu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * There ain't no way to configure spring-data repositories via pure java right now.
 * It should be a new feature in Spring Data 1.1 when it's released. 
 */
@Configuration
@ImportResource("classpath:com/impromptu/config/repository.xml")
public class RepositoryConfig {
}
