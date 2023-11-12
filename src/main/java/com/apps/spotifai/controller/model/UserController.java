package com.apps.spotifai.controller.model;

import com.apps.spotifai.model.repository.UserRepository;
import com.apps.spotifai.model.DataBase.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/find/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = userRepository.findById(id);

        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users/find/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username);

        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/users/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            userRepository.create(new User(user.getUsername(), user.getName(), user.getBirthdate(), user.getPassword()));
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/users/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User _user = userRepository.findById(id);

        if (_user != null){
            _user.setId(user.getId());
            _user.setUsername(user.getUsername());
            _user.setName(user.getName());
            _user.setBirthdate(user.getBirthdate());
            _user.setPassword(user.getPassword());

            userRepository.update(_user);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find user with id " + id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users/delete/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        try {
            int result = userRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find user with id " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("User was deleted successfully. ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete user. ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/users/delete/all")
    public ResponseEntity<String> deleteAllUsers(){
        try {
            int numRows = userRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " User(s) successfully.", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Cannot delete users.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
