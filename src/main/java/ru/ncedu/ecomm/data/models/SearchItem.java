package ru.ncedu.ecomm.data.models;

public class SearchItem {
    private String productName;
    private long search_id;
    private long productPrice;

    public SearchItem() {
    }

    public SearchItem(String productName, long search_id, long productPrice) {
        this.productName = productName;
        this.search_id = search_id;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getSearch_id() {
        return search_id;
    }

    public void setSearch_id(long search_id) {
        this.search_id = search_id;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }
}
