package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.UserDao;
import spring.entity.User;

@Service
@Transactional(readOnly=true)
public class UserService {
     @Autowired
     private UserDao userDao;
     
     public  List<User> getAllUser(){
    	  return userDao.findUserById();
     }
     @Transactional(readOnly=false)
     public void save(User user){
    	 userDao.save(user);
     }
}
