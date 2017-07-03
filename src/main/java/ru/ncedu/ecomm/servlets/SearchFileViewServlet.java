package ru.ncedu.ecomm.servlets;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "SearchFileViewServlet", urlPatterns = {"/viewSearch"})
public class SearchFileViewServlet extends HttpServlet {

    private static final int MAX_LEVEL = 6;
    private static final int FIRST_COLUMN = 1;
    private static final String SEARCH_ID = "searchId";
    private static final int ITEM_ID_COMLUMN = 1;
    private static final int ITEM_TYPE  = 2;
    private static final int ITEM_BRAND  = 3;
    private static final int ITEM_CODE  = 4;
    private static final int ITEM_NAME = 5;
    private static final int PRICE = 6;

    private String servletContext = getServletContext().getRealPath("");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);

    }

    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long searchId = Long.parseLong(req.getParameter(SEARCH_ID));

        Search search = DAOFactory
                .getDAOFactory()
                .getSearchDAO()
                .getSearchById(searchId);

        File file = new File(servletContext + search.getFilePath());

        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getOutlineLevel() > MAX_LEVEL
                    && row.getCell(FIRST_COLUMN).getCellType()
                    == Cell.CELL_TYPE_NUMERIC) {

                row.getCell(0);

            }
        }
    }
}
