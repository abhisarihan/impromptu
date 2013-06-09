package com.impromptu.profile;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.impromptu.events.EventLookupService;
import com.impromptu.users.Authenticated;
import com.impromptu.users.User;
import com.impromptu.users.UserRepository;

@Controller
public class ProfileController {

	private final UserRepository userRepository;
	private final EventLookupService eventLookupService;
	
	@Inject
	public ProfileController(UserRepository userRepo, EventLookupService eventLookupService) {
		this.userRepository = userRepo;
		this.eventLookupService = eventLookupService;
	}
	
	@RequestMapping("/profile")
	public ModelAndView showProfile(@Authenticated User user) {
		return new ModelAndView("profile/profile", "user", user).addObject("userEvents", eventLookupService.getEventsForUser(user));
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST) 
	public ModelAndView editProfile(@Authenticated User user, @ModelAttribute("user") User editedUser, BindingResult result) {
		return new ModelAndView("profile/edit", "user", user).addObject("userEvents", eventLookupService.getEventsForUser(user));
	}
	
	@RequestMapping(value="/profile/edited", method=RequestMethod.POST) 
	public String saveProfile(@Authenticated User user, @ModelAttribute("user") User editedUser, BindingResult result) {
		user.setFirstName(editedUser.getFirstName());
		user.setLastName(editedUser.getLastName());
		user.setEmail(editedUser.getEmail());
		user.setLocation(editedUser.getLocation());
		user.setInterests(editedUser.getInterests());
		System.out.println("ProfileController :: profile pic - " + editedUser.getProfilePic());
		userRepository.save(user);
		return "redirect:/";
	}
}
