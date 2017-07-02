package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.SearchDAO;
import ru.ncedu.ecomm.data.models.Builders.SearchBuilder;
import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgresSearchDAO implements SearchDAO {
    private static final String SEARCH_ID = "search_id";
    private static final String CREATION_DATE = "creation_date";
    private static final String FILE_ORIGINAL_NAME = "file_original_name";
    private static final String FILE_DIRECTORY_PATH = "file_directory_path";
    private static final String LAST_SEARCH_DATE = "last_search_date";
    private static final String FILE_PATH = "file_path";
    private static final String PRODUCT_QUANTITY = "product_quantity";
    private static final String USER_ID = "user_id";


    @Override
    public List<Search> getSearches(long userId) {
        List<Search> searches = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  search.search_id,\n" +
                            "  search.creation_date,\n" +
                            "  search.file_original_name,\n" +
                            "  search.file_directory_path,\n" +
                            "  search.last_search_date,\n" +
                            "  search.file_path,\n" +
                            "  search.product_quantity\n" +
                            "FROM search\n" +
                            "WHERE user_id = ?")){

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Search search = new SearchBuilder()
                        .setUserId(userId)
                        .setId(resultSet.getLong(SEARCH_ID))
                        .setFileOriginalName(resultSet.getString(FILE_ORIGINAL_NAME))
                        .setFileDirectoryPath(resultSet.getString(FILE_DIRECTORY_PATH))
                        .setCreationDate(resultSet.getString(CREATION_DATE))
                        .setLastSearchDate(resultSet.getString(LAST_SEARCH_DATE))
                        .setFilePath(resultSet.getString(FILE_PATH))
                        .setProductQuantity(resultSet.getLong(PRODUCT_QUANTITY))
                        .build();

                searches.add(search);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searches;
    }

    @Override
    public Search addNewSearch(Search search) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO search\n" +
                             " (user_id,\n" +
                             " file_path,\n" +
                             " file_directory_path,\n" +
                             " file_original_name,\n" +
                             " product_quantity, \n" +
                             " creation_date, \n" +
                             " last_search_date)\n" +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)\n" +
                             "RETURNING search_id")) {

            statement.setLong(1, search.getUserId());
            statement.setString(2, search.getFilePath());
            statement.setString(3, search.getFileDirectoryPath());
            statement.setString(4, search.getFileOriginalName());
            statement.setLong(5, search.getProductQuantity());
            statement.setString(6, search.getCreationDate());
            statement.setString(7, search.getLastSearchDate());
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
    public void removeSearch(long searchId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM search\n" +
                             "WHERE search_id = ?")) {
            statement.setLong(1, searchId);
            statement.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        };
    }

    @Override
    public Search getSearchById(long searchId) {
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  search.search_id,\n" +
                            "  search.user_id,\n" +
                            "  search.creation_date,\n" +
                            "  search.file_original_name,\n" +
                            "  search.last_search_date,\n" +
                            "  search.file_directory_path,\n" +
                            "  search.file_path,\n" +
                            "  search.product_quantity\n" +
                            "FROM search\n" +
                            "WHERE search_id = ?")){

            statement.setLong(1, searchId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return new SearchBuilder()
                        .setUserId(resultSet.getLong(USER_ID))
                        .setId(resultSet.getLong(SEARCH_ID))
                        .setFileOriginalName(resultSet.getString(FILE_ORIGINAL_NAME))
                        .setFileDirectoryPath(resultSet.getString(FILE_DIRECTORY_PATH))
                        .setCreationDate(resultSet.getString(CREATION_DATE))
                        .setLastSearchDate(resultSet.getString(LAST_SEARCH_DATE))
                        .setFilePath(resultSet.getString(FILE_PATH))
                        .setProductQuantity(resultSet.getLong(PRODUCT_QUANTITY))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
