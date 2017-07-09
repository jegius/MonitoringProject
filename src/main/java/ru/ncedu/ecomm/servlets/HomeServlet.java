package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Search;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private static final String USER = "user";
    private static final String REMOVE_SEARCH = "removeSearch";
    private static final String SEARCH_ID = "searchId";
    private static final String ACTION = "action";
    private static final String SEARCH_LIST = "searchList";

    private SearchService searchService = SearchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAuthorized = session.getAttribute(USER) != null;

        if (isAuthorized) {

            findAnswer(request, response);
        } else {
            request.getRequestDispatcher(Configuration.getProperty("servlet.login")).forward(request, response);
        }
    }

    private void findAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = getServletContext().getRealPath("");
        User currentUser = (User) request
                .getSession()
                .getAttribute(USER);

        if (request.getParameter(ACTION) != null) {
            switch (request.getParameter(ACTION)) {
                case REMOVE_SEARCH: {

                    searchService.removeSearch(contextPath, request.getParameter(SEARCH_ID));
                    break;
                }
            }
        } else {

            List<Search> searchList = getSearches(currentUser.getId());
            searchList = searchService.checkDataBaseOnBadFiles(contextPath, searchList);

            request.setAttribute(SEARCH_LIST, searchList);
            request.getRequestDispatcher(Configuration.getProperty("page.home")).forward(request, response);
        }
    }

    private List<Search> getSearches(long userId) {
        return DAOFactory
                .getDAOFactory()
                .getSearchDAO()
                .getSearches(userId);
    }
}
