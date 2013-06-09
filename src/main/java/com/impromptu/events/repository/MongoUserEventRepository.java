package com.impromptu.events.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.impromptu.events.Event;
import com.impromptu.users.User;

public class MongoUserEventRepository implements UserEventRepository {
	
	@Autowired
	private MongoTemplate mongo;

	@Override
	public List<Event> findByUser(User user) {
		return mongo.find(query(where("memberIds").in(user.getId())), Event.class);
	}

}
