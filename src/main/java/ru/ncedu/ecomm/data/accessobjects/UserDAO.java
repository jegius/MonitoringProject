package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    User getUserByName(String name);
}
