package com.impromptu.users.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.impromptu.users.Authenticated;
import com.impromptu.users.User;
import com.impromptu.users.UserRepository;

@Controller
@RequestMapping("/users")
public class UsersController {

  private final UserRepository userRepository;
  
  @Autowired
  public UsersController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @RequestMapping("/{userId}")
  @ResponseBody
  public User getUser(@PathVariable String userId) {
    return userRepository.findOne(userId);
  }
  
  @RequestMapping("/me")
  @ResponseBody
  public User getUser(@Authenticated User user) {
    return user;
  }
}
