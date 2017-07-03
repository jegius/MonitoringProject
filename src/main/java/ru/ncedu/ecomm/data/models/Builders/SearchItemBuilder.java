package ru.ncedu.ecomm.data.models.Builders;

import ru.ncedu.ecomm.data.models.SearchItem;

public class SearchItemBuilder {
    private long searchItemId;
    private String itemType;
    private String brand;
    private long itemCode;
    private String productName;
    private long productPrice;
    private long searchId;

    public SearchItemBuilder() {
    }

    public SearchItemBuilder setSearchItemId(long searchItemId) {
        this.searchItemId = searchItemId;

        return this;
    }

    public SearchItemBuilder setItemType(String itemType) {
        this.itemType = itemType;

        return this;
    }

    public SearchItemBuilder setBrand(String brand) {
        this.brand = brand;

        return this;
    }

    public SearchItemBuilder setItemCode(long itemCode) {
        this.itemCode = itemCode;

        return this;
    }

    public SearchItemBuilder setProductName(String productName) {
        this.productName = productName;

        return this;
    }

    public SearchItemBuilder setProductPrice(long productPrice) {
        this.productPrice = productPrice;

        return this;
    }

    public SearchItemBuilder setSearchId(long searchId) {
        this.searchId = searchId;

        return this;
    }

    public SearchItem build() {
        return new SearchItem(searchItemId,
                itemType,
                brand,
                itemCode,
                productName,
                productPrice,
                searchId);
    }
}
