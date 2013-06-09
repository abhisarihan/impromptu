package com.impromptu.events.ui;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.impromptu.events.Event;
import com.impromptu.events.EventCreationService;
import com.impromptu.events.EventDetails;
import com.impromptu.events.EventLookupService;
import com.impromptu.events.EventMembershipService;
import com.impromptu.events.EventMessageService;
import com.impromptu.users.Authenticated;
import com.impromptu.users.User;

@Controller
@RequestMapping("/events")
public class EventController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private EventLookupService eventLookupService;
	private EventCreationService eventCreationService;
	private EventMembershipService membershipService;
	private EventMessageService messageService;

	@Inject
	public EventController(EventLookupService eventLookupService,
			EventCreationService eventCreationService,
			EventMembershipService membershipService,
			EventMessageService updateService) {
		this.eventLookupService = eventLookupService;
		this.eventCreationService = eventCreationService;
		this.membershipService = membershipService;
		this.messageService = updateService;
	}

	@RequestMapping("/")
	public ModelAndView listEvents(@Authenticated User currentUser) {
		List<Event> events = eventLookupService.getEventsNearUser(currentUser);
		return new ModelAndView("events/list", "events", events);
	}

	@RequestMapping("/new")
	public ModelAndView createEventForm() {
		return new ModelAndView("events/create", "createEventForm", new Event());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createEvent(@Authenticated User currentUser,
			@Valid @ModelAttribute("createEventForm") Event event,
			BindingResult result) {
		if (result.hasErrors()) {
			return "events/create";
		}
		logger.debug("User {} creating event {}", currentUser.getId(),
				event.getId());

		Event createdEvent = eventCreationService.createEvent(currentUser,
				event);

		logger.debug("User {} user events {}", currentUser.getId(),
				eventLookupService.getEventsForUser(currentUser).size());
		return "redirect:/events/" + createdEvent.getId();
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST, params = "_cancel")
	public String cancel() {
		return "redirect:/";
	}

	@RequestMapping("/{id}")
	public ModelAndView viewEvent(@Authenticated User currentUser,
			@PathVariable String id) {
		Event event = eventLookupService.getEventDetails(id);
		boolean isMember = event.hasMember(currentUser);

		return new ModelAndView("events/view", "event", event).addObject(
				"isMember", isMember);
	}	
	
	@RequestMapping(value="/{id}", produces="application/json")
	@ResponseBody
	public EventDetails getEvent(@PathVariable String id) {
		return eventLookupService.getEventDetails(id);
	}

	@RequestMapping(value = "/{eventId}/join", method = RequestMethod.POST)
	public String joinEvent(@Authenticated User currentUser,
			@PathVariable String eventId) {
		logger.debug("User {} joining event with id {}",
				currentUser.getEmail(), eventId);

		membershipService.joinEvent(eventId, currentUser);

		return "redirect:/events/{eventId}";
	}
	
	@RequestMapping(value = "/{eventId}/leave", method = RequestMethod.POST)
	public String leaveEvent(@Authenticated User currentUser,
			@PathVariable String eventId) {
		logger.debug("User {} joining event with id {}",
				currentUser.getEmail(), eventId);

		membershipService.leaveEvent(eventId, currentUser);

		return "redirect:/events/{eventId}";
	}

	@RequestMapping(value="/{eventId}/post", method=RequestMethod.POST)
	public String postMessage(@Authenticated User currentUser,@PathVariable String eventId, String message){
		logger.debug("User {} posted message on event with id: {}", currentUser.getEmail(), eventId);
		
		messageService.addMessage(eventId, message);

		return "redirect:/events/{eventId}";
	}
}