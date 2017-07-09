package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.data.models.SearchItem;

import java.util.List;

public interface SearchDAO {

    List<Search> getSearches(long userId);

    Search addNewSearch(Search search);

    void removeSearch(long searchId);

    Search getSearchById(long searchId);

    SearchItem addNewSearchItem(SearchItem searchItem);

    void updateSearch(Search currentSearch);

    List<SearchItem> getSearchItemsBySearchId(long id);
}
