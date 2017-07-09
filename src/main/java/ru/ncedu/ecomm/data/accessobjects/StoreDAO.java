package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Store;

import java.util.List;

public interface StoreDAO {

    List<Store> getStoreList();

    Store getStoreById(long storeId);
}
