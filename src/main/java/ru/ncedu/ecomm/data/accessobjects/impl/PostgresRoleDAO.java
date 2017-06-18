package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.RoleDAO;
import ru.ncedu.ecomm.data.models.Builders.RoleBuilder;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRoleDAO implements RoleDAO {
    private static final String ROLE_ID = "role_id";
    private static final String ROLE_NAME = "name";

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  role_id,\n" +
                            "  name\n" +
                            "FROM roles");
            while (resultSet.next()) {
                Role role = new RoleBuilder()
                        .setId(resultSet.getLong(ROLE_ID))
                        .setName(resultSet.getString(ROLE_NAME))
                        .build();

                roles.add(role);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roles;
    }

    @Override
    public Role getRoleById(long id) {

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  role_id,\n" +
                            "  name\n" +
                            "FROM roles\n" +
                            "WHERE role_id = ?"
            )) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return new RoleBuilder()
                        .setName(resultSet.getString(ROLE_NAME))
                        .setId(resultSet.getLong(ROLE_ID))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
