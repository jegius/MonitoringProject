package ru.ncedu.ecomm.data;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.accessobjects.*;
import ru.ncedu.ecomm.data.accessobjects.impl.*;

public abstract class DAOFactory {

    public abstract RoleDAO getRoleDAO();

    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory() {

        switch (Configuration.getProperty("db.type")) {
            case "postgresql":
                return new DAOFactory() {

                    @Override
                    public RoleDAO getRoleDAO() {
                        return new PostgresRoleDAO();
                    }

                    @Override
                    public UserDAO getUserDAO() {
                        return new PostgresUserDAO();
                    }

                };
            default:
                throw new UnsupportedOperationException("Unsupported DAO type");
        }
    }
}
