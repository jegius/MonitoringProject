package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Builders.UserBuilder;
import ru.ncedu.ecomm.data.models.Enums.EnumRoles;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.ViewModels.UserViewModel;
import ru.ncedu.ecomm.servlets.services.UserService;
import ru.ncedu.ecomm.utils.EncryptionUtils;

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
    private static final String USER_ID = "userId";
    private static final String ROLE_ID = "roleId";
    private static final String ROLES = "roles";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String PASSWORD = "password";
    private static final String ACTION = "action";
    private static final String ACTION_ADD = "add";
    private static final String REFRESH = "refresh";
    private static final String ACTION_REMOVE = "remove";
    private static final String ACTION_UPDATE = "update";
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

    private void getAnswer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter(ACTION)) {
            case ACTION_ADD: {
                addUserOnDataBase(req, resp);
                break;
            }
            case ACTION_REMOVE: {
                removeUser(req, resp);
                break;
            }
            case ACTION_UPDATE: {
                updateUser(req, resp);
                break;
            }
            case REFRESH: {
                process(req, resp);
                break;
            }
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter(USER_ID) != null && req.getParameter(ROLE_ID) != null) {
            long userId = Long.parseLong(req.getParameter(USER_ID));
            long roleId = Long.parseLong(req.getParameter(ROLE_ID));

            User userForUpdate = DAOFactory
                    .getDAOFactory()
                    .getUserDAO()
                    .getUserById(userId);

            if (roleId != userForUpdate.getRoleId()) {
                userForUpdate.setRoleId(roleId);

                DAOFactory
                        .getDAOFactory()
                        .getUserDAO()
                        .updateUser(userForUpdate);
            }
        }
    }

    private void removeUser(HttpServletRequest req, HttpServletResponse resp) {
        long userId = Long.parseLong(req.getParameter(USER_ID));
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute(USER);
        if (userId != currentUser.getId()) {
            DAOFactory.getDAOFactory().getUserDAO().removeUser(userId);
        }
    }

    private void addUserOnDataBase(HttpServletRequest req, HttpServletResponse resp) {
        String password = EncryptionUtils.getMd5Digest(req.getParameter(PASSWORD));


        User newUser = new UserBuilder()
                .setName(req.getParameter(NAME))
                .setPassword(password)
                .setRoleId(Long.parseLong(req.getParameter(ROLE)))
                .build();

        if (validateUser(newUser)) {
            DAOFactory
                    .getDAOFactory()
                    .getUserDAO()
                    .addUser(newUser);
        }
    }

    private boolean validateUser(User user) {
        return user.getName() != null
                && user.getPassword() != null
                && user.getName().length() > 0
                && user.getPassword().length() > 0;
    }

}