package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.UserDAO;
import ru.ncedu.ecomm.data.models.User;

import java.util.List;

public class PostgreseUserDAO implements UserDAO{

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }
}
