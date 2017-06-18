package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.utils.EncryptionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String ANSWER = "answer";
    private static final String USER_ID = "userId";
    private static final String ROLE_ID = "roleId";


    private static final String ERROR_MESSAGE = "Неверное Имя пользоватея или пароль!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter(NAME);
        String password = req.getParameter(PASSWORD);

        User user = getDAOFactory().getUserDAO().getUserByName(name);
        String passwordDigest = password;
                //EncryptionUtils.getMd5Digest(password);

        if (user != null && user.getPassword().equals(passwordDigest)) {
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(ROLE_ID, user.getRoleId());
            redirectToPage(req, resp, Configuration.getProperty("servlet.home"));
        } else {
            req.setAttribute(ANSWER, ERROR_MESSAGE);
            req.getRequestDispatcher(Configuration.getProperty("page.login")).forward(req, resp);
        }
    }
}
