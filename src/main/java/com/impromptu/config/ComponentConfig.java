package com.impromptu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.impromptu.BasePackage;

/**
 * Bootstrap for the application configuration.
 */
@Configuration
@ComponentScan(basePackageClasses=BasePackage.class)
public class ComponentConfig {

}
