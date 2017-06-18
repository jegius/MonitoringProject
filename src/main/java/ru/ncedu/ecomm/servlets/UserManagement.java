package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.Enums.EnumRoles;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.servlets.ViewModels.UserViewModel;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "ManagementServlet", urlPatterns = {"/management"})
public class UserManagement extends HttpServlet {

    private static final String USER = "user";
    private static final String ROLE_ID = "roleId";
    private static final String ROLES = "roles";
    private static final String USER_VIEW_MODEL = "viewModelUser";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAnswer(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean isSuperUser = session.getAttribute(USER) != null
                && session.getAttribute(ROLE_ID).equals(EnumRoles.SUPERUSER.getRole());

        if (isSuperUser) {
            request.setAttribute(ROLES, getRoles());
            request.setAttribute(USER_VIEW_MODEL, getUsers());
            request.getRequestDispatcher(Configuration.getProperty("page.userManagement")).forward(request, response);
        } else {
            redirectToPage(request, response, Configuration.getProperty("servlet.home"));
        }
    }

    private List<Role> getRoles() {
        return getDAOFactory().getRoleDAO().getRoles();
    }

    private List<UserViewModel> getUsers() {
        return UserService.getInstance().getUsersViewModels();
    }

    private void getAnswer(HttpServletRequest req, HttpServletResponse resp) {
    }

}