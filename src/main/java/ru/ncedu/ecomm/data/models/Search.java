package ru.ncedu.ecomm.data.models;

import java.sql.Date;

public class Search {
    private long id;
    private long userId;
    private String filePath;
    private Date creationDate;
    private Date lastSearchDate;
    private long productQuantity;

    public Search() {
    }

    public Search(long id,
                  long userId,
                  String filePath,
                  Date creationDate,
                  Date lastSearchDate,
                  long productQuantity) {

        this.id = id;
        this.userId = userId;
        this.filePath = filePath;
        this.creationDate = creationDate;
        this.lastSearchDate = lastSearchDate;
        this.productQuantity = productQuantity;
    }

    public Date getLastSearchDate() {
        return lastSearchDate;
    }

    public void setLastSearchDate(Date lastSearchDate) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(long productQuantity) {
        this.productQuantity = productQuantity;
    }
}
