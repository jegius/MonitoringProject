package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Search;

import java.util.List;

public interface SearchDAO {

    List<Search> getSearches();

    Search addNewSearch(Search search);

    Search removeSearch(Search search);
}
