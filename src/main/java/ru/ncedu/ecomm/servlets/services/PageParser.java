package ru.ncedu.ecomm.servlets.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.accessobjects.StoreDAO;
import ru.ncedu.ecomm.data.models.SearchItem;
import ru.ncedu.ecomm.data.models.Store;
import ru.ncedu.ecomm.data.models.TransferObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageParser {

    private static PageParser instance;

    private StoreDAO storeDAO = DAOFactory.getDAOFactory().getStoreDAO();

    public static synchronized PageParser getInstance() {
        if (instance == null) {
            instance = new PageParser();
        }
        return instance;
    }


    public List<SearchItem> findItems(List<SearchItem> searchItems, long storeId) throws IOException {
        int matchCount = 0;
        int productStringLength = 0;



        for (SearchItem searchItem : searchItems) {
            List<TransferObject> transferObjects = new ArrayList<>();

            Store store = storeDAO.getStoreById(storeId);
            String searchLink = store.getSearchLink() + searchItem.getProductName().trim().replaceAll(" ", "+") + "&region_id=24&limit=50";

            productStringLength = searchItem.getProductName().replaceAll("[\\+|\\s]", "").length();

            Document doc = Jsoup.connect(searchLink).get();
            Elements searchElements = doc.select(".showcompare");


            for (Element element : searchElements) {
                TransferObject transferObject = new TransferObject();
                Element productLink = element.select(".sel-product-tile-title").first();

                String  linkAttribute = productLink.attr("data-product-info");


                Pattern namePattern = Pattern.compile("(?<=\"productName\":)([^\\,]+)");
                Pattern pricePattern = Pattern.compile("(?<=\"productPriceLocal\":)([^\\,]+)");

                Matcher nameMatcher = namePattern.matcher(linkAttribute);
                Matcher priceMatcher = pricePattern.matcher(linkAttribute);

                if (nameMatcher.find()) {
                    transferObject.setName(nameMatcher.group());
                }

                if (priceMatcher.find()) {
                    transferObject.setPrice(priceMatcher.group());
                }


                transferObjects.add(transferObject);
            }

        }
        return searchItems;
    }


}
