package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
@Service
public class AccountCreationService {
    UserInfo u1 = new UserInfo(1,"dashang","dashang","abc","dashang_2526@yahoo.com");
    UserInfo u2 = new UserInfo(2,"Nirav","n1","abc","dashang_2526@yahoo.com");
    UserInfo u3 = new UserInfo(3,"Harshal","h1","abc","dashang_2526@yahoo.com");
    UserInfo u4 = new UserInfo(4,"Paras","p1","abc","dashang_2526@yahoo.com");

    UserInfo obj = new UserInfo();
    ObjectMapper mapper = new ObjectMapper();

    HashMap <String,UserInfo> map = new HashMap<String,UserInfo>();
    public String writeToJasonObj()throws Exception{
            return mapper.writeValueAsString(u1);
    }

    public  void saveUserInfo(UserInfo userData){
        String randomUUID = UUID.randomUUID().toString();
        map.put(randomUUID,userData);
    }


}
