package com.impromptu.search;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.impromptu.events.Event;
import com.impromptu.events.EventSearchService;
import com.impromptu.users.Authenticated;
import com.impromptu.users.User;

@Controller
@RequestMapping("/search")
public class SearchController {

	private final EventSearchService eventSearchService;
	
	@Inject
	public SearchController(EventSearchService eventSearchService) {
		this.eventSearchService = eventSearchService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchEvent(@Authenticated User currentUser, @RequestParam("q") String search) {
		List<Event> matchingEvents = eventSearchService.findByNameOrDescription(search);
		
		if (matchingEvents.isEmpty()) {
		    return new ModelAndView("redirect:/events/new");
		}
		
		return new ModelAndView("home", "events", matchingEvents);
    }
}
