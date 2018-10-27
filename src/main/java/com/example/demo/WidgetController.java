package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RestController
public class WidgetController {

    @Autowired
    AccountCreationService accountCreationService;

    @RequestMapping(value = "id", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String index(){
        return "{\"data\": \"Hello World\"}";
    }

    @RequestMapping(value = "value", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String indexByValue(){
        return "{\"data\": \"Hello Value\"}";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserInfo createAccount(@RequestBody UserInfo userInfo){
       // return "{\"id\": \"1\",\"name\": \"Dashang\", \"user\": \"Dashang\",\"password\": \"abc\" , \"email\": \"dashang_2526@yahoo.com\" }";
        accountCreationService.saveUserInfo(userInfo);
        return userInfo;
    }
}
