package com.impromptu.events;

import java.util.List;

import com.impromptu.users.User;

public interface EventLookupService {
	
	EventDetails getEventDetails(String eventId);

	List<Event> getAllEvents();
	
	List<Event> getEventsForUser(User user);
	
	List<Event> getEventsNearUser(User user);
	
}
