package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.StoreDAO;
import ru.ncedu.ecomm.data.models.Builders.StoreBuilder;
import ru.ncedu.ecomm.data.models.Store;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresStoreDAO implements StoreDAO {
    private static final String STORE_ID = "id";
    private static final String STORE_NAME = "name";
    private static final String SEARCH_BASE_LINK = "search_base_link";

    @Override
    public List<Store> getStoreList() {
        List<Store> stores = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  id,\n" +
                            "  name,\n" +
                            "  search_base_link\n" +
                            "FROM stores");


            while (resultSet.next()) {
                Store store = new StoreBuilder()
                        .setId(resultSet.getLong(STORE_ID))
                        .setName(resultSet.getString(STORE_NAME))
                        .setSearchLink(resultSet.getString(SEARCH_BASE_LINK))
                        .build();

                stores.add(store);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stores;
    }

    @Override
    public Store getStoreById(long storeId) {
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  id,\n" +
                            "  name,\n" +
                            "  search_base_link\n" +
                            "FROM stores\n" +
                            "WHERE id = ?")){

            statement.setLong(1, storeId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return new StoreBuilder()
                        .setId(resultSet.getLong(STORE_ID))
                        .setName(resultSet.getString(STORE_NAME))
                        .setSearchLink(resultSet.getString(SEARCH_BASE_LINK))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
