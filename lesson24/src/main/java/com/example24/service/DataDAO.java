package com.example24.service;

import java.util.HashMap;
import java.util.Map;

import com.example24.model.User;

import javax.servlet.RequestDispatcher;


public class DataDAO {

    private static final Map<String, User> mapUsers = new HashMap<String, User>();

    static {
        initUsers();
    }

    private static void initUsers() {

        User emp = new User("User1", "123");
        User mng = new User("User2", "123");
        mapUsers.put(emp.getName(), emp);
        mapUsers.put(mng.getName(), mng);

    }

    // Find a User by userName and password.
    public static User findUser(String name, String password) {
        User u = mapUsers.get(name);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

}
