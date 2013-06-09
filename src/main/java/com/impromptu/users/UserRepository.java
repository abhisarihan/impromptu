package com.impromptu.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	// FIXME: This should be secured
	User findByEmailAndPassword(String email, String password);
}
