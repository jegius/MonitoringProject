package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.SearchDAO;
import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresSearchDAO implements SearchDAO {
    @Override
    public List<Search> getSearches() {
        return null;
    }

    @Override
    public Search addNewSearch(Search search) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO search\n" +
                             " (user_id,\n" +
                             " file_path,\n" +
                             " file_original_name,\n" +
                             " product_quantity, \n" +
                             " creation_date, \n" +
                             " last_search_date)\n" +
                             "VALUES (?, ?, ?, ?, ?, ?)\n" +
                             "RETURNING search_id")) {

            statement.setLong(1, search.getUserId());
            statement.setString(2, search.getFilePath());
            statement.setString(3, search.getFileOriginalName());
            statement.setLong(4, search.getProductQuantity());
            statement.setString(5, search.getCreationDate());
            statement.setString(6, search.getLastSearchDate());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                search.setId(resultSet.getLong(1));

                return search;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return search;
    }

    @Override
    public Search removeSearch(Search search) {
        return null;
    }
}
