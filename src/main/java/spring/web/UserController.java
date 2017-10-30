package spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.entity.User;
import spring.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
     private UserService userService;
     @RequestMapping("/list")
     public List<User> getAllUser(){
    	 return userService.getAllUser();
     }
     @RequestMapping("/save")
     private String save(@RequestParam("name")String name,@RequestParam("password")String password){
    	  User user=new User();
    	  user.setName(name);
    	  user.setPassword(password);
    	  userService.save(user);
    	  return "插入成功";
     }
}
