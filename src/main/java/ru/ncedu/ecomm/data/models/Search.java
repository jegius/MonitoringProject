package ru.ncedu.ecomm.data.models;

import java.util.List;

public class Search {
    private long id;
    private long userId;
    private int searchStatus;
    private String filePath;
    private String fileDirectoryPath;
    private String fileOriginalName;
    private String creationDate;
    private String lastSearchDate;
    private long productQuantity;
    private List<SearchItem> searchItemList;

    public Search() {
    }

    public Search(long id,
                  long userId,
                  int searchStatus,
                  String filePath,
                  String fileDirectoryPath,
                  String fileOriginalName,
                  String creationDate,
                  String lastSearchDate,
                  long productQuantity,
                  List<SearchItem> searchItemList) {

        this.id = id;
        this.userId = userId;
        this.searchStatus = searchStatus;
        this.filePath = filePath;
        this.fileDirectoryPath = fileDirectoryPath;
        this.fileOriginalName = fileOriginalName;
        this.creationDate = creationDate;
        this.lastSearchDate = lastSearchDate;
        this.productQuantity = productQuantity;
        this.searchItemList = searchItemList;
    }

    public int getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(int searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getFileDirectoryPath() {
        return fileDirectoryPath;
    }

    public List<SearchItem> getSearchItemList() {
        return searchItemList;
    }

    public void setSearchItemList(List<SearchItem> searchItemList) {
        this.searchItemList = searchItemList;
    }

    public void setFileDirectoryPath(String fileDirectoryPath) {
        this.fileDirectoryPath = fileDirectoryPath;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getLastSearchDate() {
        return lastSearchDate;
    }

    public void setLastSearchDate(String lastSearchDate) {
        this.lastSearchDate = lastSearchDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(long productQuantity) {
        this.productQuantity = productQuantity;
    }
}
