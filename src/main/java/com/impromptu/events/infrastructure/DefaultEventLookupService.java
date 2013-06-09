package com.impromptu.events.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impromptu.events.Event;
import com.impromptu.events.EventDetails;
import com.impromptu.events.EventLookupService;
import com.impromptu.events.repository.EventRepository;
import com.impromptu.users.User;
import com.impromptu.users.UserRepository;

@Service
public class DefaultEventLookupService implements EventLookupService {

	private final EventRepository eventRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public DefaultEventLookupService(EventRepository eventRepository, UserRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}
	
	@Override
    public EventDetails getEventDetails(String eventId) {
        Event event = eventRepository.findOne(eventId);
        EventDetails eventDetails = EventDetails.forEvent(event);
        
        populateUsers(eventDetails.getMembers(), event.getMemberIds());
        populateUsers(eventDetails.getAdmins(), event.getAdminIds());
        
        return eventDetails;
    }
	
	private void populateUsers(Set<User> users, Iterable<String> userIds) {
	    for (String userId : userIds) {
            User user = userRepository.findOne(userId);
            if (user != null) {
                users.add(user);
            }
        }
	}
	
	@Override
	public List<Event> getEventsForUser(User user) {
		return toList(eventRepository.findByMemberIds(user.getId()));
	}

	@Override
	public List<Event> getAllEvents() {
		return toList(eventRepository.findAll());
	}

	@Override
	public List<Event> getEventsNearUser(User user) {
		return toList(eventRepository.findByLocation(user.getLocation()));
	}
	
	private List<Event> toList(Iterable<Event> events) {
		ArrayList<Event> eventList = new ArrayList<Event>();
		for (Event e : events) {
			eventList.add(e);
		}
		return eventList;
	}
}
