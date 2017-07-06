package ru.ncedu.ecomm.servlets;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Builders.SearchItemBuilder;
import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.data.models.SearchItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "SearchFileViewServlet", urlPatterns = {"/view"})
public class SearchFileViewServlet extends HttpServlet {
    private static final int NO_SEARCH_ITEMS = 0;
    private static final int ITEMS_ALREADY_EXIST = 1;
    private static final int SEARCH_LIST_LOADED_TO_BASE = 1;
    private static final String SEARCH_ID = "searchId";
    private Search search;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doAction(req, resp);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doAction(req, resp);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

    }

    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, InvalidFormatException {
        String servletContext = getServletContext().getRealPath("");
        long lastColumnIndex;
        long searchId = Long.parseLong(req.getParameter(SEARCH_ID));
        List<SearchItem> searchItemList = new ArrayList<>();

        search = getSearchFromDAO(searchId);

        switch (search.getSearchStatus()){
            case NO_SEARCH_ITEMS: {
                break;
            }
        }

        File file = new File(servletContext + search.getFilePath());

        try (FileInputStream inputStream = new FileInputStream(file);
             Workbook workbook = new HSSFWorkbook(inputStream)) {
            Sheet firstSheet = workbook.getSheetAt(0);

            for (Row nextRow : firstSheet) {

                if (nextRow.getCell(0) == null ||
                        nextRow.getCell(nextRow.getFirstCellNum()).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                    continue;
                }
                lastColumnIndex = nextRow.getLastCellNum();
                ArrayList<String> cellValues = new ArrayList<>();

                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() == 0 ||
                            cell.getColumnIndex() == lastColumnIndex - 2 ||
                            cell.getColumnIndex() == lastColumnIndex - 1) {
                        cellValues.add(getCellValue(cell) == null
                                ? "0"
                                : getCellValue(cell));
                    }
                }

                searchItemList.add(DAOFactory
                        .getDAOFactory()
                        .getSearchDAO()
                        .addNewSearchItem(createNewSearchItem(cellValues)));

                search.setSearchStatus(SEARCH_LIST_LOADED_TO_BASE);
                updateSearch(search);
            }
        }

        req.getRequestDispatcher(Configuration.getProperty("page.viewSearch")).forward(req, resp);
    }

    private SearchItem createNewSearchItem(ArrayList<String> cellValues) {
        return new SearchItemBuilder()
                .setSearchId(search.getId())
                .setSearchItemId(convertStringToLong(cellValues.get(0)))
                .setProductName(cellValues.get(1).split("\\(")[0].replaceAll("[А-Яа-я]", ""))
                .setProductPrice(cellValues.get(2) != null ? convertStringToLong(cellValues.get(2)) : 0)
                .build();
    }

    private long convertStringToLong(String string) {
        return Long.parseLong(string.split("\\.")[0]);
    }

    private Search getSearchFromDAO(long searchId) {
        return DAOFactory
                .getDAOFactory()
                .getSearchDAO()
                .getSearchById(searchId);
    }

    private void updateSearch(Search currentSearch) {
        DAOFactory
                .getDAOFactory()
                .getSearchDAO()
                .updateSearch(currentSearch);
    }

    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: {
                return cell.getStringCellValue();

            }
            case Cell.CELL_TYPE_BOOLEAN: {
                return Boolean.toString(cell.getBooleanCellValue());

            }
            case Cell.CELL_TYPE_NUMERIC: {
                return Double.toString(cell.getNumericCellValue());

            }
        }
        return null;
    }
}
