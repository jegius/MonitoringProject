package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.Enums.EnumRoles;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class UserManagement extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean userInSystem = UserService.getInstance().isUserAuthorized(request);
        Boolean userAreAdministrator = UserService.getInstance().redirectIfNotAllowed(request, EnumRoles.SUPERUSER.getRole());
        if (!userInSystem && userAreAdministrator) {
            request.getRequestDispatcher("/home").forward(request, response);
        }
    }
}