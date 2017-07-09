package ru.ncedu.ecomm.data;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.accessobjects.*;
import ru.ncedu.ecomm.data.accessobjects.impl.*;

public abstract class DAOFactory {

    public abstract RoleDAO getRoleDAO();

    public abstract UserDAO getUserDAO();

    public abstract SearchDAO getSearchDAO();

    public abstract StoreDAO getStoreDAO();

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

                    @Override
                    public SearchDAO getSearchDAO() {
                        return new PostgresSearchDAO();
                    }

                    @Override
                    public StoreDAO getStoreDAO() {
                        return new PostgresStoreDAO();
                    }
                };
            default:
                throw new UnsupportedOperationException("Unsupported DAO type");
        }
    }
}
