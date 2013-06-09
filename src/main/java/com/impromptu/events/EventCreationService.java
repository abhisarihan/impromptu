package com.impromptu.events;

import com.impromptu.users.User;

public interface EventCreationService {

	Event createEvent(User creator, Event event);
	
}
