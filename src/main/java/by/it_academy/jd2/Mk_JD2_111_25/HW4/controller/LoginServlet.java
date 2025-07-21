package by.it_academy.jd2.Mk_JD2_111_25.HW4.controller;

import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.ActiveUserListener;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.UserService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.UserStorageDB;
import by.it_academy.jd2.Mk_JD2_111_25.HW4.storage.api.IUserStorage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        IUserStorage storage = new UserStorageDB();
        IUserService service = new UserService();


        try {
            String error = service.authenticate(login, password);
            if (error != "") {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/template/signin.jsp").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user", storage.get(login));
                ActiveUserListener.userLoggedIn(session);
                resp.sendRedirect(req.getContextPath().concat("/ui/user/chats"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

