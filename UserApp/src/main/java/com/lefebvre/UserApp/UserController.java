package com.lefebvre.UserApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public List<User> getUser(){
        return userRepository.findAll();
    }

    @RequestMapping("/userid/{id}")
    public List<User> getUserById(@PathVariable("id") BigInteger id){
        return userRepository.findByuserid(id);
    }

    @RequestMapping("/courseid/{id}")
    public List<User> getUserByCourseId(@PathVariable("id") BigInteger id){
        return userRepository.findBycourseid(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/users")
    public User saveUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/usersid/{id}")
    public void deleteUser(@PathVariable BigInteger id){
        userRepository.deleteById(id);
    }

}
