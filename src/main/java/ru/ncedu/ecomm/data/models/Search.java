package ru.ncedu.ecomm.data.models;

import java.sql.Date;

public class Search {
    private long id;
    private long userId;
    private Date creationDate;
    private long productQuantity;

    public Search() {
    }

    public Search(long id, long userId, Date creationDate, long productQuantity) {
        this.id = id;
        this.userId = userId;
        this.creationDate = creationDate;
        this.productQuantity = productQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
