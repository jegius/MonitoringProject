package ru.ncedu.ecomm.servlets;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Builders.SearchBuilder;
import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.data.models.User;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String USER = "user";
    private static final String ERROR = "error";
    private static final String ANSWER = "answer";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String PATTERN = ".xls";
    private static final String WIN_1251 = "windows-1251";
    private static final String UTF_8 = "UTF-8";
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private static final String ERROR_BAD_FILE_TYPE = "Неверный формат файла! Добавьте файл в формате .xls";
    private static final String ERROR_DOES_NOT_CONTAIN_DATA = "Запрос пуст!";
    private static final String ERROR_WAS_AN_ERROR = "Возникла ошибка: ";
    private static final String UPLOAD_SUCCESSFUL = "Файл успешно загружен!";

    /**
     * handles file upload via HTTP POST method
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAuthorized = session.getAttribute(USER) != null;

        Pattern regex = Pattern.compile(PATTERN);

        if (isAuthorized) {

            User thisUser = (User) session.getAttribute(USER);

            // checks if the request actually contains upload file
            if (!ServletFileUpload.isMultipartContent(request)) {
                request.setAttribute(ERROR, ERROR_DOES_NOT_CONTAIN_DATA);
                request.getRequestDispatcher(Configuration.getProperty("servlet.home")).forward(request, response);
                return;
            }

            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY
                    + File.separator + thisUser.getId()
                    + File.separator + new Date().getTime();


            // creates the directory if it does not exist

            try {
                // parses the request's content to extract file data
                List<FileItem> formItems = upload.parseRequest(request);

                // iterates over form's fields
                for (FileItem item : formItems) {

                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new String(new File(item.getName())
                                .getName()
                                .getBytes(WIN_1251), UTF_8);

                        Matcher regexMatcher = regex.matcher(fileName);
                        if (!regexMatcher.find()) {
                            request.setAttribute(ERROR, ERROR_BAD_FILE_TYPE);
                            request.getRequestDispatcher(Configuration.getProperty("servlet.home")).forward(request, response);
                            return;
                        } else {

                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                            }
                        }

                        String filePath = uploadPath
                                + File.separator
                                + new Date().getTime()
                                + PATTERN;

                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                        String date = simpleDateFormat.format(new Date(DATE_FORMAT));

                        Search search = new SearchBuilder()
                                .setCreationDate(date)
                                .setFilePath(uploadPath)
                                .setFileOriginalName(fileName)
                                .setUserId(thisUser.getId())
                                .build();

                        addSearch(search);
                    }
                }
                request.setAttribute(ANSWER, UPLOAD_SUCCESSFUL);
                request.getRequestDispatcher(Configuration.getProperty("servlet.home")).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute(ERROR, ERROR_WAS_AN_ERROR + ex.getMessage());
                request.getRequestDispatcher(Configuration.getProperty("servlet.home")).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(Configuration.getProperty("page.login")).forward(request, response);
        }
    }

    private void addSearch(Search search){
        DAOFactory
                .getDAOFactory()
                .getSearchDAO()
                .addNewSearch(search);
    }
}