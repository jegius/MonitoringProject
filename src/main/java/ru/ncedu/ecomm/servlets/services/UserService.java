package ru.ncedu.ecomm.servlets.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    public Boolean redirectIfNotAllowed(HttpServletRequest request, long role) {
        HttpSession session = request.getSession();
        long userRoleId = Long.parseLong(session.getAttribute("userRoleId").toString());
        return userRoleId == role;
    }
}
