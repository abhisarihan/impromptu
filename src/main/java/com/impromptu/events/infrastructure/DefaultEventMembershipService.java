package com.impromptu.events.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impromptu.events.Event;
import com.impromptu.events.EventMembershipService;
import com.impromptu.events.exceptions.EventNotFoundException;
import com.impromptu.events.repository.EventRepository;
import com.impromptu.users.User;

@Service
public class DefaultEventMembershipService implements EventMembershipService {
	
	private final EventRepository eventRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public DefaultEventMembershipService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Event leaveEvent(String eventId, User member) {
		assertUser(member);

		Event event = getSingleEvent(eventId);
		
		boolean removed = event.getMemberIds().remove(member.getId());
		
		if (removed) {
			logger.debug("Removing {} from event {}", member, event);
			return eventRepository.save(event);
		} else {
			logger.debug("{} didn't belong in {} anyway!", member, event);
			return event;
		}
	}

	@Override
	public Event joinEvent(String eventId, User user) {
		assertUser(user);
		
		Event event = getSingleEvent(eventId);
		
		boolean added = event.getMemberIds().add(user.getId());
		
		if (added) {
			logger.debug("Adding {} to the event {}", user, event);
			return eventRepository.save(event);
		} else {
			logger.debug("{} is already a member of {}", user, event);
			return event;
		}
	}
	
	private Event getSingleEvent(String eventId) {
	    Event event = eventRepository.findOne(eventId);
        if (event == null) {
            throw new EventNotFoundException(eventId);
        }
        return event;
	}
	
	private static void assertUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
	}

}
