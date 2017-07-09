package ru.ncedu.ecomm.data.models;

public class Store {
    long id;
    String name;
    String searchLink;

    public Store(long id, String name, String searchLink) {
        this.id = id;
        this.name = name;
        this.searchLink = searchLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchLink() {
        return searchLink;
    }

    public void setSearchLink(String searchLink) {
        this.searchLink = searchLink;
    }
}
