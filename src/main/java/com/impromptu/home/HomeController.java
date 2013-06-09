package com.impromptu.home;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.impromptu.events.EventLookupService;
import com.impromptu.users.Authenticated;
import com.impromptu.users.User;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private EventLookupService eventListingService;
	
	@Inject
	public HomeController(EventLookupService eventListingService) {
		this.eventListingService = eventListingService;
	}
	
	@RequestMapping("")
	public ModelAndView displayEvents(@Authenticated User user) {
		if (user == null) {
			return new ModelAndView("home", "events", eventListingService.getAllEvents());
		}
		return new ModelAndView("home", "events", eventListingService.getEventsNearUser(user)).addObject("userEvents", eventListingService.getEventsForUser(user));
	}
	
	@RequestMapping("about")
	public ModelAndView aboutPage() {
		return new ModelAndView("about");
	}
}
