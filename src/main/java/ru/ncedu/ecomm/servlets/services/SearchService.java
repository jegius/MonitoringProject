package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.accessobjects.SearchDAO;
import ru.ncedu.ecomm.data.models.Search;

import java.io.File;
import java.util.List;

public class SearchService {
    private static SearchService instance;

    private static SearchDAO searchDAO = DAOFactory
            .getDAOFactory()
            .getSearchDAO();

    public static synchronized SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    public List<Search> checkDataBaseOnBadFiles(String contextPath, List<Search> searches) {

        for (Search currentSearch : searches) {
            if (!new File(contextPath + currentSearch.getFilePath()).exists()) {

                searchDAO.removeSearch(currentSearch.getId());
            }
        }
        return searches;
    }

    public void removeSearch(String servletContext, String parameter) {
        Search search = searchDAO.getSearchById(Long.parseLong(parameter));

        File file = new File(servletContext + search.getFileDirectoryPath().trim());
        recursiveDelete(file);

        searchDAO.removeSearch(Long.parseLong(parameter));
    }

    private static void recursiveDelete(File file) {
        // до конца рекурсивного цикла
        if (!file.exists())
            return;

        //если это папка, то идем внутрь этой папки и вызываем рекурсивное удаление всего, что там есть
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                // рекурсивный вызов
                recursiveDelete(f);
            }
        }
        // вызываем метод delete() для удаления файлов и пустых(!) папок
        file.delete();
        System.out.println("[DEBUG] Удален файл или папка: " + file.getAbsolutePath());
    }
}
