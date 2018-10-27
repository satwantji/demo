package com.example.demo;

import java.util.List;

public interface UserService {

        UserInfo findById(long id);

        UserInfo findByName(String name);

        void saveUser(UserInfo user);

        void updateUser(UserInfo user);

        void deleteUserById(long id);

        List<UserInfo> findAllUsers();

        void deleteAllUsers();

        boolean isUserExist(UserInfo user);
}
