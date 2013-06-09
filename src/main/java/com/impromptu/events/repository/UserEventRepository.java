package com.impromptu.events.repository;

import java.util.List;

import com.impromptu.events.Event;
import com.impromptu.users.User;

public interface UserEventRepository {

	List<Event> findByUser(User user);
	
}
