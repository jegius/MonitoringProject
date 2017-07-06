package ru.ncedu.ecomm.data.models;

public class SearchItem {
    private long searchItemId;
    private String productName;
    private long productPrice;
    private long searchId;

    public SearchItem() {
    }

    public SearchItem(long searchItemId,
                      String productName,
                      long productPrice,
                      long searchId) {

        this.searchItemId = searchItemId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.searchId = searchId;
    }

    public long getSearchItemId() {
        return searchItemId;
    }

    public void setSearchItemId(long searchItemId) {
        this.searchItemId = searchItemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getSearchId() {
        return searchId;
    }

    public void setSearchId(long search_id) {
        this.searchId = searchId;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }
}
