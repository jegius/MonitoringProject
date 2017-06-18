package ru.ncedu.ecomm.servlets.services;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public Boolean isUserAuthorized(HttpServletRequest request) {
        return false;
    }

}
