package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.UserDAO;
import ru.ncedu.ecomm.data.models.Builders.UserBuilder;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {

    private static final String ID = "user_id";
    private static final String ROLE_ID = "role_id";
    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";



    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  user_id,\n" +
                            "  role_id,\n" +
                            "  password,\n" +
                            "  login\n" +
                            "FROM users");
            while (resultSet.next()) {
                User user = new UserBuilder()
                        .setId(resultSet.getLong(ID))
                        .setRoleId(resultSet.getLong(ROLE_ID))
                        .setPassword(resultSet.getString(PASSWORD))
                        .setName(resultSet.getString(LOGIN))
                        .build();

                users.add(user);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }


    @Override
    public User getUserByName(String name) {

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  user_id,\n" +
                            "  role_id,\n" +
                            "  password,\n" +
                            "  login\n" +
                            "FROM users\n" +
                            "WHERE login = ?")){

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return new UserBuilder()
                        .setId(resultSet.getLong(ID))
                        .setRoleId(resultSet.getLong(ROLE_ID))
                        .setPassword(resultSet.getString(PASSWORD))
                        .setName(resultSet.getString(LOGIN))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    @Override
    public User addUser(User user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users\n" +
                             "(role_id,\n" +
                             " password,\n" +
                             " login)\n" +
                             "VALUES (?, ?, ?, ?)\n" +
                             "RETURNING user_id")) {

            statement.setLong(1, user.getRoleId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));

                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
