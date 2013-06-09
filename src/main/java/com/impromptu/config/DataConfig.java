package com.impromptu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.Mongo;

@Configuration
@EnableTransactionManagement
public class DataConfig {
    
	@Autowired
	private Mongo mongo;
	
    @Bean
    public MongoFactoryBean mongo() {
    	MongoFactoryBean mongo = new MongoFactoryBean();
    	mongo.setHost("localhost");
    	return mongo;
    }
    
    @Bean
    public MongoDbFactory mongoDbFactory() {
    	return new SimpleMongoDbFactory(mongo, "impromptu-db");
    }
    
    public @Bean MongoTemplate mongoTemplate() {
    	return new MongoTemplate(mongoDbFactory());
	}
}
