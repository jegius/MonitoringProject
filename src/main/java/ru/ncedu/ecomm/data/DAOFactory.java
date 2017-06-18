package ru.ncedu.ecomm.data;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.accessobjects.UserDAO;
import ru.ncedu.ecomm.data.accessobjects.impl.PostgreseUserDAO;

public abstract class DAOFactory {

    public abstract UserDAO getUserDAO();

    // public abstract CategoryDAO getCategoryDAO();
    // another DAO...

    public static DAOFactory getDAOFactory() {

        switch (Configuration.getProperty("db.type")) {
            case "postgresql":
                return new DAOFactory() {

                    @Override
                    public UserDAO getUserDAO() {
                        return new PostgreseUserDAO();
                    }

                };
            default:
                throw new UnsupportedOperationException("Unsupported DAO type");
        }
    }
}
