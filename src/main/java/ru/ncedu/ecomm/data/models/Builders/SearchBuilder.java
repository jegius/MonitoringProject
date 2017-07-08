package ru.ncedu.ecomm.data.models.Builders;

import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.data.models.SearchItem;

import java.util.List;

public class SearchBuilder {
    private long id;
    private long userId;
    private int searchStatus;
    private String fileOriginalName;
    private String filePath;
    private String fileDirectoryPath;
    private String creationDate;
    private String lastSearchDate;
    private long productQuantity;
    private List<SearchItem> searchItemList;

    public SearchBuilder() {
    }

    public SearchBuilder setFileDirectoryPath(String fileDirectoryPath) {
        this.fileDirectoryPath = fileDirectoryPath;

        return this;
    }

    public SearchBuilder setSearchStatus(int searchStatus) {
        this.searchStatus = searchStatus;

        return this;
    }

    public SearchBuilder setId(long id) {
        this.id = id;

        return this;
    }

    public SearchBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public SearchBuilder setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;

        return this;
    }

    public SearchBuilder setFilePath(String filePath) {
        this.filePath = filePath;

        return this;
    }

    public SearchBuilder setCreationDate(String creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public SearchBuilder setLastSearchDate(String lastSearchDate) {
        this.lastSearchDate = lastSearchDate;

        return this;
    }

    public SearchBuilder setProductQuantity(long productQuantity) {
        this.productQuantity = productQuantity;

        return this;
    }

    public SearchBuilder setSearchItemList(List<SearchItem> searchItemList) {
        this.searchItemList = searchItemList;

        return this;
    }

    public Search build(){
        return new Search(
                id,
                userId,
                searchStatus,
                filePath,
                fileDirectoryPath,
                fileOriginalName,
                creationDate,
                lastSearchDate,
                productQuantity,
                searchItemList
        );
    }
}
