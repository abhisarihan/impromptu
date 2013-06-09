package com.impromptu.events.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impromptu.events.Event;
import com.impromptu.events.EventCreationService;
import com.impromptu.events.repository.EventRepository;
import com.impromptu.users.User;

@Service
public class DefaultEventCreationService implements EventCreationService {

	private final EventRepository eventRepository;
	
	@Autowired
	public DefaultEventCreationService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Override
	public Event createEvent(User creator, Event event) {
		event.getMemberIds().add(creator.getId());
		event.getAdminIds().add(creator.getId());
		return eventRepository.save(event);
	}

}
