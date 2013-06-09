package com.impromptu.registration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.impromptu.users.User;
import com.impromptu.users.UserRepository;

@Controller
public class RegistrationController {
    
    private final UserRepository userRepository;
    
    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/register")
    public ModelAndView registerUserForm() {
        return new ModelAndView("registration/form", "registerUserForm", new RegisterUserForm());
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST) 
    public String registerUser(@Valid RegisterUserForm registerUserForm, BindingResult result) {
        if(result.hasErrors()) {
            registerUserForm.setPassword("");
            registerUserForm.setConfirmPassword("");
            return "registration/form";
        }
        userRepository.save(new User(registerUserForm));
        
        // TODO: log in the newly created user
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/register", method=RequestMethod.POST, params="_cancel")
    public String cancel() {
        return "redirect:/";
    }
}
