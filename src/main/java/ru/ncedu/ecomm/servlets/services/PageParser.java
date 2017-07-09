package ru.ncedu.ecomm.servlets.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.ncedu.ecomm.data.models.SearchItem;

import java.io.IOException;
import java.util.List;

public class PageParser {

    private static final String M_VIDEO_LINK = "http://www.mvideo.ru/product-list-page-cls?q=";
    private static PageParser instance;

    public static synchronized PageParser getInstance() {
        if (instance == null) {
            instance = new PageParser();
        }
        return instance;
    }


    public List<SearchItem> findItems(List<SearchItem> searchItems, long storeId) throws IOException {

        String searchLink = M_VIDEO_LINK + searchItems.get(0).getProductName().trim().replaceAll(" ", "+");

        Document doc = Jsoup.connect(M_VIDEO_LINK).get();
        Elements newsHeadlines = doc.select("#mp-itn b a");

        return searchItems;
    }
}
