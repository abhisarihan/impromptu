package com.impromptu.events.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impromptu.events.Event;
import com.impromptu.events.EventMessageService;
import com.impromptu.events.repository.EventRepository;

@Service
public class DefaultEventMessageService implements EventMessageService {

private final EventRepository eventRepository;
	
	@Autowired
	public DefaultEventMessageService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
    @Override
    public Event addMessage(String eventId, String message) {
        Event event = eventRepository.findOne(eventId);
        List<String> messages = event.getMessages();
        messages.add(message);
        event.setMessages(messages);
        return eventRepository.save(event);
    }

}
