package ru.ncedu.ecomm.data.models.Builders;

import ru.ncedu.ecomm.data.models.Store;

public class StoreBuilder {
    private long id;
    private String name;
    private String searchLink;

    public StoreBuilder() {
    }

    public StoreBuilder setId(long id) {
        this.id = id;

        return this;
    }

    public StoreBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public StoreBuilder setSearchLink(String searchLink) {
        this.searchLink = searchLink;

        return this;
    }

    public Store build(){
        return new Store(
                id,
                name,
                searchLink
        );
    }
}
