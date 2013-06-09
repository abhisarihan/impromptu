package com.impromptu.events.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.impromptu.events.Event;

public interface EventRepository extends CrudRepository<Event, String> {
	
	List<Event> findByMemberIds(String userId);
	
	List<Event> findByEventDescriptionLike(String eventDescription);
	
	List<Event> findByEventNameLike(String eventName);
	
	List<Event> findByLocation(String location);
}
