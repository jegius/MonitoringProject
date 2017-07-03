package ru.ncedu.ecomm.data.models;

public class SearchItem {
    private long searchItemId;
    private String itemType;
    private String brand;
    private long itemCode;
    private String productName;
    private long productPrice;
    private long searchId;

    public SearchItem() {
    }

    public SearchItem(long searchItemId,
                      String itemType,
                      String brand,
                      long itemCode,
                      String productName,
                      long productPrice,
                      long searchId) {

        this.searchItemId = searchItemId;
        this.itemType = itemType;
        this.brand = brand;
        this.itemCode = itemCode;
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getItemCode() {
        return itemCode;
    }

    public void setItemCode(long itemCode) {
        this.itemCode = itemCode;
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
