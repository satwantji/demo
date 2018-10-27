package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Service("userService")
public class UserServiceImpl implements UserService{
    private static final AtomicLong counter = new AtomicLong();

    private static List<UserInfo> users;

    static{
        users= populateDummyUsers();
    }
    public List<UserInfo> findAllUsers() {
        System.out.println("Find all user called ");
        return users;
    }

    public UserInfo findById(long id) {
        for(UserInfo user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public UserInfo findByName(String name) {
        for(UserInfo user : users){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void saveUser(UserInfo user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updateUser(UserInfo user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserById(long id) {

        for (Iterator<UserInfo> iterator = users.iterator(); iterator.hasNext(); ) {
            UserInfo user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(UserInfo user) {
        return findByName(user.getName())!=null;
    }

    public void deleteAllUsers(){
        users.clear();
    }

    private static List<UserInfo> populateDummyUsers(){
        List<UserInfo> users = new ArrayList<UserInfo>();
        users.add(new UserInfo(counter.incrementAndGet(),"dashang","dashang","abc","dashang_2526@yahoo.com"));
        users.add(new UserInfo(counter.incrementAndGet(),"jugal","jugal","abc","jugal@yahoo.com"));
        users.add(new UserInfo(counter.incrementAndGet(),"paras","paras","abc","paras@yahoo.com"));
        users.add(new UserInfo(counter.incrementAndGet(),"nirav","nirav","abc","nirav@yahoo.com"));
        return users;
    }
}
