package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private static final String USER = "user";

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
            request.getRequestDispatcher(Configuration.getProperty("page.home")).forward(request, response);
        } else {
            request.getRequestDispatcher(Configuration.getProperty("page.login")).forward(request, response);
        }
    }
}
