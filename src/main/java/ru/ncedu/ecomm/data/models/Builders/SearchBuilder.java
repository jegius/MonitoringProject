package ru.ncedu.ecomm.data.models.Builders;

import ru.ncedu.ecomm.data.models.Search;

import java.sql.Date;

public class SearchBuilder {
    private long id;
    private long userId;
    private String filePath;
    private Date creationDate;
    private Date lastSearchDate;
    private long productQuantity;

    public SearchBuilder() {
    }

    public SearchBuilder setId(long id) {
        this.id = id;

        return this;
    }

    public SearchBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public SearchBuilder setFilePath(String filePath) {
        this.filePath = filePath;

        return this;
    }

    public SearchBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public SearchBuilder setLastSearchDate(Date lastSearchDate) {
        this.lastSearchDate = lastSearchDate;

        return this;
    }

    public SearchBuilder setProductQuantity(long productQuantity) {
        this.productQuantity = productQuantity;

        return this;
    }

    public Search build(){
        return new Search(
                id,
                userId,
                filePath,
                creationDate,
                lastSearchDate,
                productQuantity
        );
    }
}
