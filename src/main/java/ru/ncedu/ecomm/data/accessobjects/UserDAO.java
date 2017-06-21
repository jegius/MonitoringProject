package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    User getUserByName(String name);

    User addUser(User user);

    User updateUser(User user);

    void removeUser(long userId);

    User getUserById(long userId);
}
