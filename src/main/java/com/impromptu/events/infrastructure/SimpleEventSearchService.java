package com.impromptu.events.infrastructure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impromptu.events.Event;
import com.impromptu.events.EventSearchService;
import com.impromptu.events.repository.EventRepository;

@Service
public class SimpleEventSearchService implements EventSearchService {

    private final EventRepository eventRepository;
    
    @Autowired
    public SimpleEventSearchService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    @Override
    public List<Event> findByNameOrDescription(String text) {
        
        // HACK. TODO: Do this in the repository where search is more efficient.
        
        List<Event> nameMatches = eventRepository.findByEventNameLike(text);
        List<Event> descriptionMatches = eventRepository.findByEventDescriptionLike(text);
        HashSet<Event> matches = new HashSet<Event>();
        matches.addAll(nameMatches);
        matches.addAll(descriptionMatches);
        return Arrays.asList(matches.toArray(new Event[]{}));
    }
}
