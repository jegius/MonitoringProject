package ru.ncedu.ecomm.servlets.services;

public class UserService {


    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

}
