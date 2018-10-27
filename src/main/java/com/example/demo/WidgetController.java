package com.example.demo;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@RestController
@RequestMapping("/api")
public class WidgetController {

    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
    /*AccountCreationService accountCreationService;

    @RequestMapping(value = "id", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String index(){
        return "{\"data\": \"Hello World\"}";
    }*/

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserInfo>> listAllUsers() {
        List<UserInfo> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
    }
    // single user
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id {}" + id);
        UserInfo user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id {} not found." + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserInfo user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User : {}"+user);

        if (userService.isUserExist(user)) {
            System.out.println("Unable to create. A User with name {} already exist"+ user.getName());
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserInfo user) {
        System.out.println("Updating User with id {}"+id);

        UserInfo currentUser = userService.findById(id);

        if (currentUser == null) {
            System.out.println("Unable to update. User with id {} not found." + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());

        userService.updateUser(currentUser);
        return new ResponseEntity<UserInfo>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id {}" + id);

        UserInfo user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id {} not found." + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<UserInfo> deleteAllUsers() {
        System.out.println("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
    }

    /*@RequestMapping(value = "value", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String indexByValue(){
        return "{\"data\": \"Hello Value\"}";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserInfo createAccount(@RequestBody UserInfo userInfo){
       // return "{\"id\": \"1\",\"name\": \"Dashang\", \"user\": \"Dashang\",\"password\": \"abc\" , \"email\": \"dashang_2526@yahoo.com\" }";
        accountCreationService.saveUserInfo(userInfo);
        return userInfo;
    }*/
}
